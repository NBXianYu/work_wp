package com.core.work.utils;

import com.core.work.entity.SysUserEntity;
import com.core.work.exception.BaseException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @Description: Shiro工具类
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/13 0013 下午 14:54
 */
public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SysUserEntity getUserEntity() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getUserId() {
        return getUserEntity().getId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        if (kaptcha == null) {
            throw new BaseException("验证码已失效");
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }

}
