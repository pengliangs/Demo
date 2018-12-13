package com.pl.job;

import com.pl.demo.d1.HelloJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author pengliang  2018-06-09 15:51
 */
public class TestJob1 implements Job {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("TestJob1运行中：{} - {}",Thread.currentThread().getName(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
