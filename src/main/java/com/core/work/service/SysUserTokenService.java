package com.core.work.service;

import com.core.work.utils.Result;

/**
 * @author: 成都阳帆网络科技有限公司
 * @date: 2018/8/21 0021 10:32
 * @description: 用户token
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
