package com.core.work.utils;

import com.core.work.entity.SheduleJobEntity;
import com.core.work.exception.BaseException;
import com.core.work.repository.SheduleJobRepository;
import com.core.work.taskjob.SysUserJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Component
public class SheduleUtils {

    private static Scheduler scheduler;

    private static SheduleJobRepository sheduleJobRepository;

    static {
        try {
            scheduler = new org.quartz.impl.StdSchedulerFactory().getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 定时启动用户定时器
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [name, startTime]
     * @return void
     * @date 2019/2/22 0022 上午 11:50
     */
    public static void createUserTaskJob(String name,Date startTime){
        try{
            sheduleJobRepository = (SheduleJobRepository) SpringContextUtils.getBean("sheduleJobRepository");
            SheduleJobEntity sheduleJobEntity=null;

            //绑定日常工作实例id
            String uuid=UUID.randomUUID().toString().replaceAll("-", "");

            JobDetail jobDetail= JobBuilder.newJob(SysUserJob.class).
                    withIdentity(JobKey.jobKey(uuid)).
                    usingJobData("id", name).build();
            //触发时间为计划实例的开始时间
            SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                    .withIdentity(TriggerKey.triggerKey(uuid))
                    .startAt(startTime)
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().
                            withMisfireHandlingInstructionFireNow()).build();
            scheduler.scheduleJob(jobDetail,simpleTrigger);

            if(sheduleJobRepository.findByNameAndType(name,1)==null){
                sheduleJobEntity=new SheduleJobEntity();
                sheduleJobEntity.setName(name);
                sheduleJobEntity.setStartTime(startTime);
                sheduleJobEntity.setType(1);
            }else{
                sheduleJobEntity=sheduleJobRepository.findByNameAndType(name,1);
            }
            sheduleJobEntity.setKey(uuid);
            sheduleJobRepository.save(sheduleJobEntity);


        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("创建用户定时器失败");
        }
    }

    public static Scheduler getScheduler() {
        return scheduler;
    }

    public static void setScheduler(Scheduler scheduler) {
        SheduleUtils.scheduler = scheduler;
    }

    @Autowired
    public  void setSheduleJobRepository(SheduleJobRepository sheduleJobRepository) {
        SheduleUtils.sheduleJobRepository = sheduleJobRepository;
    }
}
