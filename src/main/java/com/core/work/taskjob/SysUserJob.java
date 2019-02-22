package com.core.work.taskjob;

import com.alibaba.fastjson.JSONObject;
import com.core.work.exception.BaseException;
import com.core.work.service.SheduleJobService;
import com.core.work.utils.SheduleUtils;
import com.core.work.utils.SpringContextUtils;
import com.core.work.entity.SysUserEntity;
import com.core.work.service.SysUserService;
import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * @Author 吴鹏
 * @Date Created in 上午 10:49 2019/2/20 0020
 * @Email 694798354@qq.com
 * @Description:
 */
@Component
public class SysUserJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SheduleJobService sheduleJobService = (SheduleJobService) SpringContextUtils.getBean("sheduleJobServiceImpl");
        SysUserService sysUserService = (SysUserService) SpringContextUtils.getBean("sysUserServiceImpl");
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String userId = dataMap.getString("id");
        SysUserEntity sysUserEntity = sysUserService.findById(userId);
        System.out.println(sysUserEntity.toString());
        System.out.println("学生信息：" + JSONObject.toJSON(sysUserEntity).toString());

        //流程启动后删除sheduleJobEntity
        sheduleJobService.deleteByName(userId);
        try {
            //移除job
            SheduleUtils.getScheduler().deleteJob(context.getJobDetail().getKey());
            //移除触发器
            SheduleUtils.getScheduler().unscheduleJob(context.getTrigger().getKey());
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new BaseException("移除计划定时器失败");
        }
    }
}
