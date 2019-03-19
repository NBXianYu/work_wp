package com.core.work.service;

import com.core.work.utils.Result;

/**
 * @Description: 用户token
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/19 0019 下午 14:10
 */
public interface SysUserTokenService {

    /**
     * 生成token
     *
     * @param userId 用户ID
     */
    Result createToken(String userId);

    /**
     * 退出，修改token值
     *
     * @param userId 用户ID
     */
    void logout(String userId);

}
