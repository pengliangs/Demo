package com.pl.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * author pengliang  2018-06-09 15:19
 */
@Component
public class QuartzDefault implements QuartzAbility {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(JobParameter jobParameter) throws SchedulerException {
        String jobName = jobParameter.getJobName();
        String jobGroupName = jobParameter.getJobGroupName();
        String cron = jobParameter.getCron();
        //构建一个Job作业
        JobDetail jobDetail = JobBuilder.newJob(jobParameter.getJobClass()).withIdentity(jobName, jobGroupName).build();

        //构建一个cron表达式，定义具体执行时间
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);

        //根据cron表达式，构建一个触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        //将任务和触发器注册到调度中心
        scheduler.scheduleJob(jobDetail, trigger);

        // 启动调度器
        scheduler.start();
    }

    @Override
    public void rescheduleJob(JobParameter jobParameter) throws SchedulerException {
        //获取到指定调度器
        TriggerKey triggerKey = TriggerKey.triggerKey(jobParameter.getJobName(), jobParameter.getJobGroupName());
        //定义具体执行规则
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobParameter.getCron());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    @Override
    public void deleteJob(JobParameter jobParameter) throws SchedulerException {
        String jobName = jobParameter.getJobName();
        String jobGroupName = jobParameter.getJobGroupName();
        //用给定的键暂停触发器。
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
        //从调度器中删除指示的触发器。
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroupName));
        //从调度器中删除已识别的作业和相关联的触发器。
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
    }

    @Override
    public void pauseJob(JobParameter jobParameter) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobParameter.getJobName(), jobParameter.getJobGroupName()));
    }

    @Override
    public void restartJob(JobParameter jobParameter) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobParameter.getJobName(), jobParameter.getJobGroupName()));
    }

}
