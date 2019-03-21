package com.core.work.repository;

import com.core.work.entity.SysUserEntity;

/**
 * @Author：吴鹏
 * Description:
 *
 * @Author
 * @Date 2018/12/21 0021 16:48
 */
public interface SysUserRepository extends BaseRepository<SysUserEntity, String> {

    /**
     * @Description: 根据手机号查询用户
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [mobile]
     * @return com.core.work.entity.SysUserEntity
     * @date 2019/3/13 0013 上午 11:02
     */
    SysUserEntity findByPhone(String mobile);
    /**
     * @Description: 根据用户名查询用户
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [mobile]
     * @return com.core.work.entity.SysUserEntity
     * @date 2019/3/13 0013 上午 11:02
     */
    SysUserEntity findByUserName(String userName);

    /**
     * @Description: 根据用户名判断用户是否存在
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [mobile]
     * @return com.core.work.entity.SysUserEntity
     * @date 2019/3/13 0013 上午 11:02
     */
    boolean existsByUserName(String userName);
}
