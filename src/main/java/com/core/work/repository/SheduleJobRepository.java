package com.core.work.repository;


import com.core.work.entity.SheduleJobEntity;
import com.core.work.entity.SysUserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SheduleJobRepository extends JpaRepository<SheduleJobEntity, String>, JpaSpecificationExecutor<SheduleJobEntity> {


    void deleteByName(String name);
    List<SheduleJobEntity> findByName(String name);
    SheduleJobEntity findByNameAndType(String name, int type);

    /**
     * @Auther: Theo
     * @Date: 2018/8/27 0027 15:58
     * @Description:
     */
    interface SysUserTokenRepository extends JpaRepository<SysUserTokenEntity,String>, JpaSpecificationExecutor<SysUserTokenEntity> {

        SysUserTokenEntity findByUserId(String userId);

        SysUserTokenEntity findByToken(String token);
    }
}
