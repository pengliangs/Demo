package com.pl.demo.d1;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author pengliang  2018-06-09 15:27
 */
public class HelloJob implements Job {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("HelloJob运行中：{}",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
