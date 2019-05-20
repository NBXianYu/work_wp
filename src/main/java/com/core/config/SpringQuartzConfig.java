package com.core.config;

import com.core.modules.sys.service.SysUserService;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Author 吴鹏
 * @Date Created in 上午 9:49 2019/3/13 0013
 * @Email 694798354@qq.com
 * @Description: 静态定时器加载配置
 */
@Configuration
@EnableScheduling
public class SpringQuartzConfig {

    @Bean(name = "testJobDetail")
    public MethodInvokingJobDetailFactoryBean testJobDetail(SysUserService sysUserService) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(sysUserService);
        // 需要执行的方法
        jobDetail.setTargetMethod("testQuartz");
        // 需要传入的参数
        jobDetail.setArguments("啊哈");
        return jobDetail;
    }

    @Bean(name = "testJobTrigger")
    public CronTriggerFactoryBean testJobTrigger(JobDetail testJobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(testJobDetail);
        //设置任务启动延迟
        trigger.setStartDelay(0);
        // 秒 分 小时 日期 月份 星期 年
        trigger.setCronExpression("0 40 * * * ? *");
        return trigger;
    }

    // 配置Scheduler
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger testJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
        // 注册触发器
        bean.setTriggers(testJobTrigger);
        return bean;
    }

}
