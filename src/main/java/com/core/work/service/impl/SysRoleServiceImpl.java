package com.core.work.service.impl;

import com.core.work.entity.SysRoleEntity;
import com.core.work.repository.SysRoleRepository;
import com.core.work.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author 吴鹏
 * @Date Created in 上午 11:45 2019/2/18 0018
 * @Email 694798354@qq.com
 * @Description:
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleEntity, SysRoleRepository> implements SysRoleService {
    @Autowired
    SysRoleRepository sysRoleRepository;

    @Override
    public List<SysRoleEntity> findByParams(Map params) {
        return null;
    }

}
