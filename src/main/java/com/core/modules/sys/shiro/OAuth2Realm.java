package com.core.modules.sys.shiro;

import com.core.modules.sys.entity.SysUserEntity;
import com.core.modules.sys.entity.SysUserTokenEntity;
import com.core.modules.sys.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Description:
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/1/28 0028 下午 18:11
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    private final ShiroService shiroService;

    @Autowired
    public OAuth2Realm(ShiroService shiroService) {
        this.shiroService = shiroService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /***
     * @Description: 第六步：登录成功后，则调用doGetAuthorizationInfo方法
     *                                  查询用户的权限，再调用具体的 接口，整个流程结束
     * @date 2019/1/28 0028 下午 18:11
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity) principals.getPrimaryPrincipal();
        String userId = user.getId();

        // 用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     * 第四步：第二步的subject.login(token)执行的时候会调用这个方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        // 根据accessToken，查询用户信息
        SysUserTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
        // Token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        // 查询用户信息
        SysUserEntity user = shiroService.queryUser(tokenEntity.getUserId());
        // 账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        // 将用户信息保存起来，可以通过(SysUserEntity) SecurityUtils.getSubject().getPrincipal()获取
        return new SimpleAuthenticationInfo(user, accessToken, getName());
    }
}
