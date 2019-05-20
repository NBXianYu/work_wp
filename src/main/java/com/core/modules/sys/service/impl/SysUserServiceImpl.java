package com.core.modules.sys.service.impl;

import com.core.common.exception.BaseException;
import com.core.common.utils.Result;
import com.core.modules.sys.repository.SysUserRepository;
import com.core.modules.sys.entity.SysUserEntity;
import com.core.modules.sys.entity.vo.SysUserVo;
import com.core.modules.sys.service.SysUserService;
import com.core.modules.sys.service.spec.SysUserSpec;
import com.core.common.utils.DateUtils;
import com.core.common.utils.JpaPageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description:
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @Param:
 * @return
 * @date 2019/1/28 0028 下午 17:11
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserEntity, SysUserRepository> implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;


    @Override
    public void addSysUser(SysUserEntity sysUserEntity) {
        if (sysUserRepository.existsByUserName(sysUserEntity.getUserName())){
            throw new BaseException("当前用户名已存在，请更换");
        }
        sysUserRepository.save(sysUserEntity);
    }

    @Override
    public Result getHome(Map<String, Object> params){

        Page<SysUserEntity> entityPage =
                sysUserRepository.findAll(SysUserSpec.where(params), JpaPageUtils.getPageRequest(params));
        List<SysUserVo> sysUserVoList = new ArrayList<>();
        // 遍历结果
        entityPage.stream().map(value -> SysUserVo.getHomeVoByEntity(value)).forEach(sysUserVoList::add);

        return Result.ok().putResult(sysUserVoList).putTotal(entityPage.getTotalElements());
    }

    @Override
    public void testQuartz(String param) {
        System.out.println("静态定时器执行成功：" + DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println("传入参数值为：" + param);
    }

    @Override
    public SysUserEntity queryByName(String userName) {
        return sysUserRepository.findByUserName(userName);
    }
}
