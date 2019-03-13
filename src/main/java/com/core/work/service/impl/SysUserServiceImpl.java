package com.core.work.service.impl;

import com.core.work.entity.SysUserEntity;
import com.core.work.repository.SysUserRepository;
import com.core.work.service.SysUserService;
import com.core.work.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @Param:
 * @return
 * @date 2019/1/28 0028 下午 17:11
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserEntity, SysUserRepository> implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public List<SysUserEntity> findByParams(Map params) {
        return sysUserRepository.findAll();
    }

    @Override
    public void addSysUser(SysUserEntity sysUserEntity) {
        sysUserRepository.save(sysUserEntity);
    }

    @Override
    public void testQuartz(String param) {
        System.out.println("静态定时器执行成功：" + DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println("传入参数值为：" + param);
    }
}
