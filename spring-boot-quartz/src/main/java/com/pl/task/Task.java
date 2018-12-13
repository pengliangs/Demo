package com.pl.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author pengliang  2018-06-10 15:08
 */
@Component
public class Task {

    //@Scheduled(cron = "0/1 * * * * ?")
    public void task1() {
        System.out.println("task1：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
