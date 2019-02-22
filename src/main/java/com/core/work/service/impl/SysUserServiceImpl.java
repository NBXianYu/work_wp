package com.core.work.service.impl;

import com.core.work.entity.SysUserEntity;
import com.core.work.repository.SysUserRepository;
import com.core.work.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public List<SysUserEntity> findAll() {
        return sysUserRepository.findAll();
    }

    @Override
    public List<SysUserEntity> findByParams(Map params) {
        return sysUserRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<String> ids) {
        sysUserRepository.deleteById(ids.get(0));
    }

    @Override
    public SysUserEntity findById(String id) {
        return sysUserRepository.findById(id).orElse(null);
    }

    @Override
    public void addSysUser(SysUserEntity sysUserEntity) {
        sysUserRepository.save(sysUserEntity);
    }
}
