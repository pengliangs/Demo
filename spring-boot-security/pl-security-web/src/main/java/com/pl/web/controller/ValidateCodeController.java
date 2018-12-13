package com.pl.web.controller;

import com.pl.core.validate.code.ImageCode;
import com.pl.core.validate.code.ImageGenerate;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * author pengliang  2018-05-20 10:13
 */
@RequestMapping("/code")
@Controller
public class ValidateCodeController {


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ImageCode imageCode = ImageGenerate.createImageCode();
        sessionStrategy.setAttribute(new ServletWebRequest(request),ImageGenerate.SESSION_IMAGE_KEY,imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }


}
