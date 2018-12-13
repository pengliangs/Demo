package com.pl;

import com.pl.demo.d1.HelloJob;
import com.pl.job.TestJob1;
import com.pl.quartz.JobParameter;
import com.pl.quartz.QuartzAbility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzApplicationTests {

    @Autowired
    private QuartzAbility quartzAbility;


    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Test
    public void test1() throws SchedulerException {
        quartzAbility.addJob(new JobParameter(TestJob1.class, "testJob1", "testJobGroup", "0/1 * * * * ?"));
    }

    @Test
    public void test2() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(scheduleBuilder)
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();
    }

}
