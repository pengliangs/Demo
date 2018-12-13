package com.pl.quartz;

import lombok.Data;
import org.quartz.Job;

/**
 * author pengliang  2018-06-09 15:11
 */
@Data
public class JobParameter {

    private Class<? extends Job> jobClass;

    private String jobName;

    private String jobGroupName;

    private String cron;

    public JobParameter(String jobName, String jobGroupName) {
        this(null, jobName, jobGroupName, null);
    }
    public JobParameter(String jobName, String jobGroupName,String cron) {
        this(null, jobName, jobGroupName, cron);
    }
    public JobParameter(Class<? extends Job> jobClass, String jobName, String jobGroupName, String cron) {
        this.jobClass = jobClass;
        this.jobName = jobName;
        this.jobGroupName = jobGroupName;
        this.cron = cron;
    }
}
