package com.core.modules.sys.service.impl;

import com.core.modules.sys.repository.SysRoleRepository;
import com.core.modules.sys.entity.SysRoleEntity;
import com.core.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
