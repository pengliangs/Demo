package com.github.pengliangs.email.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author pengliang on 2018-12-13 09:38
 */
@Data
public class SendEmailDto implements Serializable {

    /**
     * 接收人
     */
    @NotBlank(message = "接收人不可为空")
    private String to;

    /**
     * 标题
     */
    @NotBlank(message = "标题不可为空")
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不可为空")
    private String content;

    /**
     * 附件
     */
    @JsonIgnore
    private MultipartFile file;
}
