package com.core.modules.job.repository;


import com.core.modules.job.entity.SheduleJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SheduleJobRepository extends JpaRepository<SheduleJobEntity, String>, JpaSpecificationExecutor<SheduleJobEntity> {


    void deleteByName(String name);
    List<SheduleJobEntity> findByName(String name);
    /**
     * @Description: name存id    type目前1为SysUserEntity
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [name, type]
     * @return SheduleJobEntity
     * @date 2019/3/11 0011 下午 17:04
     */
    SheduleJobEntity findByNameAndType(String name, int type);
}
