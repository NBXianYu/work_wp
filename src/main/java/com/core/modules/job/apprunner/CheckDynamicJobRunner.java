package com.core.modules.job.apprunner;


import com.core.modules.job.entity.SheduleJobEntity;
import com.core.modules.job.repository.SheduleJobRepository;
import com.core.common.utils.SheduleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 项目启动后检查是否有未执行定时器，有就启动它
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @Param:
 * @return
 * @date 2019/3/12 0012 上午 11:56
 */
@Component
public class CheckDynamicJobRunner implements ApplicationRunner {

    @Autowired
    private SheduleJobRepository sheduleJobRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("----------------------------------------------------------检查动态定时器开始-----------------------------------------------------------------------");
        List<SheduleJobEntity>  sheduleJobEntityList=sheduleJobRepository.findAll();
        for (SheduleJobEntity sheduleJobEntity : sheduleJobEntityList) {
            SheduleUtils.createUserTaskJob(sheduleJobEntity.getName(),sheduleJobEntity.getStartTime());
        }
        int a=sheduleJobEntityList.size();
        System.out.println("--------------------------------------------检查动态定时器结束,共有"+a+"个动态定时器被重启------------------------------------------------------------");
    }
}
