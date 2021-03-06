package com.core.work.service;

import com.core.work.entity.SysUserEntity;
import com.core.work.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * @author : 成都阳帆网络科技有限公司
 * @date : 2018/8/19 21:41
 * @description : Shiro 相关接口
 */
public interface ShiroService {
    /**
     * @Description: 获取用户权限列表
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @param: userId
     * @return java.util.Set<java.lang.String>
     * @date 2019/1/30 0030 下午 18:11
     */
    Set<String> getUserPermissions(String userId);

    /**
     * @Description:
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [token]
     * @return com.core.work.entity.SysUserTokenEntity
     * @date 2019/1/30 0030 下午 18:11
     */
    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId
     */
    SysUserEntity queryUser(String userId);
}
