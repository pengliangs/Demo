package com.pl.demo.d1;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * author pengliang  2018-06-09 15:24
 */
public class SimpleQuartz {

    public static void main(String[] args) throws SchedulerException {
        //获取任务调度器
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        //创建一个任务作业
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(scheduleBuilder)
                .build();

        //将任务和触发器注册到调度中
        sched.scheduleJob(job, trigger);

        //启动调度程序
        sched.start();
    }
}
