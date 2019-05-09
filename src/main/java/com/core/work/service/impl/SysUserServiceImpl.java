package com.core.work.service.impl;

import com.core.work.entity.SysUserEntity;
import com.core.work.entity.vo.SysUserVo;
import com.core.work.exception.BaseException;
import com.core.work.repository.SysUserRepository;
import com.core.work.service.SysUserService;
import com.core.work.service.spec.SysUserSpec;
import com.core.work.utils.DateUtils;
import com.core.work.utils.JpaPageUtils;
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
    public Map<String, Object> getHome(Map<String, Object> params){

        Page<SysUserEntity> entityPage =
                sysUserRepository.findAll(SysUserSpec.where(params), JpaPageUtils.getPageRequest(params));
        Map<String, Object> resultMap = new HashMap<>(5);
        List<SysUserVo> sysUserVoList = new ArrayList<>();
        // 遍历结果
        entityPage.stream().map(value -> SysUserVo.getHomeVoByEntity(value)).forEach(sysUserVoList::add);

        resultMap.put("entityList",sysUserVoList);
        resultMap.put("totalElements",entityPage.getTotalElements());
        resultMap.put("totalPages",entityPage.getTotalPages());
        resultMap.put("size",entityPage.getSize());

        return resultMap;
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
