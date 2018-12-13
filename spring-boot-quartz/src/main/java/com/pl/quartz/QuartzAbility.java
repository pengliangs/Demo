package com.pl.quartz;

import org.quartz.SchedulerException;

/**
 * author pengliang  2018-06-09 15:11
 */
public interface QuartzAbility {

    /**
     * 添加一个新的任务
     * @param jobParameter
     * @throws SchedulerException
     */
    void addJob(JobParameter jobParameter) throws SchedulerException;

    /**
     * 重置更新指定任务
     * @param jobParameter
     * @throws SchedulerException
     */
    void rescheduleJob(JobParameter jobParameter) throws SchedulerException;

    /**
     * 删除指定任务
     * @param jobParameter
     * @throws SchedulerException
     */
    void deleteJob(JobParameter jobParameter) throws SchedulerException;

    /**
     * 暂停指定任务
     * @param jobParameter
     * @throws SchedulerException
     */
    void pauseJob(JobParameter jobParameter) throws SchedulerException;

    /**
     * 恢复已暂停任务
     * @param jobParameter
     * @throws SchedulerException
     */
    void restartJob(JobParameter jobParameter) throws SchedulerException;


}
