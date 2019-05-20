package com.core.modules.sys.service.impl;

import com.core.modules.sys.repository.SysUserTokenRepository;
import com.core.modules.sys.service.ShiroService;
import com.core.modules.sys.entity.SysUserEntity;
import com.core.modules.sys.entity.SysUserTokenEntity;
import com.core.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/***
 * @Description:
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @Param:
 * @return
 * @date 2019/1/30 0030 下午 18:27
 */
@Service
public class ShiroServiceImpl implements ShiroService {
    private final SysUserService sysUserService;
    private final SysUserTokenRepository sysUserTokenRepository;


    @Autowired
    public ShiroServiceImpl(SysUserService sysUserService, SysUserTokenRepository sysUserTokenRepository) {
        this.sysUserService = sysUserService;
        this.sysUserTokenRepository = sysUserTokenRepository;
    }

    @Override
    public Set<String> getUserPermissions(String userId) {
        /**
         * PS: 后期菜单管理使用，先不做，毕竟还没弄前端
         */
        return null;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenRepository.findByToken(token);
    }

    @Override
    public SysUserEntity queryUser(String userId) {
        return sysUserService.findById(userId);
    }
}
