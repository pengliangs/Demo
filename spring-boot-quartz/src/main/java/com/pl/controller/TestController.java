package com.pl.controller;

import com.pl.job.TestJob1;
import com.pl.quartz.JobParameter;
import com.pl.quartz.QuartzAbility;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author pengliang  2018-06-09 16:35
 */
@RestController
public class TestController {

    @Autowired
    private QuartzAbility quartzAbility;

    private final String JOB_NAME = "testJob1";

    private final String JOB_GROUP_NAME = "testJobGroup";


    @RequestMapping("/addJob")
    public String addJob() throws SchedulerException {
        quartzAbility.addJob(new JobParameter(TestJob1.class, JOB_NAME, JOB_GROUP_NAME, "0/1 * * * * ?"));
        return "addJob";
    }

    @RequestMapping("/rescheduleJob")
    public String rescheduleJob() throws SchedulerException {
        quartzAbility.rescheduleJob(new JobParameter(JOB_NAME, JOB_GROUP_NAME,"0/3 * * * * ?"));
        return "rescheduleJob";
    }

    @RequestMapping("/deleteJob")
    public String deleteJob() throws SchedulerException {
        quartzAbility.deleteJob(new JobParameter(JOB_NAME, JOB_GROUP_NAME));
        return "deleteJob";
    }

    @RequestMapping("/pauseJob")
    public String pauseJob() throws SchedulerException {
        quartzAbility.pauseJob(new JobParameter(JOB_NAME, JOB_GROUP_NAME));
        return "pauseJob";
    }

    @RequestMapping("/restartJob")
    public String restartJob() throws SchedulerException {
        quartzAbility.restartJob(new JobParameter(JOB_NAME, JOB_GROUP_NAME));
        return "restartJob";
    }
}
