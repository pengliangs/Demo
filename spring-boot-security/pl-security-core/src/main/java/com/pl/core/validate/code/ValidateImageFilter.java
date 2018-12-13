package com.pl.core.validate.code;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片验证码校验
 * author pengliang  2018-05-20 09:58
 */
@Data
public class ValidateImageFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler;

    public ValidateImageFilter() {

    }

    public ValidateImageFilter(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("OncePerRequestFilter.doFilterInternal");
        //只对登陆请求进行处理
        if (StringUtils.equals(request.getRequestURI(), "/user/login/form")) {
            try {
                validateImageCode(new ServletWebRequest(request));
                filterChain.doFilter(request, response);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
            return;
        }
        filterChain.doFilter(request, response);
    }

    public void validateImageCode(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode imageCodeSession = (ImageCode) sessionStrategy.getAttribute(request, ImageGenerate.SESSION_IMAGE_KEY);
        String imageCodeIn = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(imageCodeIn)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (imageCodeSession == null || StringUtils.isBlank(imageCodeSession.getCode())) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (imageCodeSession.isExpired()) {
            sessionStrategy.removeAttribute(request, ImageGenerate.SESSION_IMAGE_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(imageCodeIn, imageCodeSession.getCode().toLowerCase())) {
            throw new ValidateCodeException("验证输入错误");
        }

        sessionStrategy.removeAttribute(request, ImageGenerate.SESSION_IMAGE_KEY);
    }
}
