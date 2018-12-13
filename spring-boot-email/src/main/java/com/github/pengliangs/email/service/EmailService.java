package com.github.pengliangs.email.service;

import com.github.pengliangs.email.dto.SendEmailDto;

/**
 * @author pengliang on 2018-12-13 10:09
 */
public interface EmailService {

    boolean send(SendEmailDto sendEmailDto);

}
