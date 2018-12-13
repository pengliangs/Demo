package com.github.pengliangs.email.controller;

import com.github.pengliangs.email.dto.SendEmailDto;
import com.github.pengliangs.email.response.ResultData;
import com.github.pengliangs.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengliang on 2018-12-13 09:29
 */
@RestController
@RequestMapping("/send")
public class SendEmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/simpleEmail")
    public ResultData simpleEmail(@Validated SendEmailDto sendEmailDto, Errors errors) {
        if (errors.hasErrors()){
            return ResultData.newResultData(400, errors.getFieldError().getDefaultMessage());
        }
        if (emailService.send(sendEmailDto)) {
            return ResultData.newResultData(200, "发送成功", sendEmailDto);
        }
        return ResultData.newResultData(500, "发送失败", sendEmailDto);
    }

}
