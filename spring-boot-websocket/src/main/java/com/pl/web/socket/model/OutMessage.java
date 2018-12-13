package com.pl.web.socket.model;

import java.util.Date;

/**
 * author pengliang  2018-05-27 14:51
 */
public class OutMessage {

    private String form;

    private String content;

    private Date time = new Date();

    public OutMessage() {
    }

    public OutMessage(String form, String content) {
        this.form = form;
        this.content = content;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
