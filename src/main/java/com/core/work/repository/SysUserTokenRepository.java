package com.core.work.repository;

import com.core.work.entity.SysUserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author 吴鹏
 * @Date Created in 下午 16:54 2019/3/11 0011
 * @Email 694798354@qq.com
 * @Description:
 */
public interface SysUserTokenRepository extends JpaRepository<SysUserTokenEntity,String>, JpaSpecificationExecutor<SysUserTokenEntity> {

    SysUserTokenEntity findByUserId(String userId);

    SysUserTokenEntity findByToken(String token);
}
