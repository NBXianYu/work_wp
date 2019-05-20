package com.core.modules.sys.service;

import com.core.modules.sys.entity.SysUserEntity;
import org.springframework.stereotype.Service;

/***
 * @Description:
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @Param:
 * @return
 * @date 2019/1/28 0028 下午 15:21
 */
@Service
public interface SysUserService extends BaseService<SysUserEntity>{

    /**
     * @Description: 添加用户
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [sysUserEntity]
     * @return void
     * @date 2019/3/13 0013 上午 11:00
     */
    void addSysUser(SysUserEntity sysUserEntity);

    /**
     * @Description: 测试静态定时器方法
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [param]
     * @return void
     * @date 2019/3/13 0013 上午 11:00
     */
    void testQuartz(String param);

    /**
     * @Description: 根据用户名查询用户
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [mobile]
     * @return SysUserEntity
     * @date 2019/3/13 0013 上午 11:00
     */
    SysUserEntity queryByName(String userName);

}
