package com.core.work.service;

import com.core.work.entity.SysUserEntity;
import org.springframework.stereotype.Service;

/***
 * @Description:
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @Param:
 * @return
 * @date 2019/1/28 0028 下午 15:21
 */
@Service
public interface SysUserService extends BaseService<SysUserEntity>{

    void addSysUser(SysUserEntity sysUserEntity);

    void testQuartz(String param);

}
