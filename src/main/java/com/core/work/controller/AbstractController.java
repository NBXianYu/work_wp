package com.core.work.controller;

import com.core.work.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author 吴鹏
 * @Date Created in 下午 15:11 2019/1/28 0028
 * @Email 694798354@qq.com
 * @Description:
 */
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected String getUserId() {
        return getUser().getId();
    }

}
