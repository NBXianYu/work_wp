package com.core.modules.sys.service.impl;

import com.core.common.utils.JpaPageUtils;
import com.core.common.utils.Result;
import com.core.modules.sys.entity.SysLogEntity;
import com.core.modules.sys.repository.SysLogRepository;
import com.core.modules.sys.service.SysLogService;
import com.core.modules.sys.service.spec.SysLogSpec;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author 吴鹏
 * @Date Created in 上午 11:45 2019/2/18 0018
 * @Email 694798354@qq.com
 * @Description:
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogEntity, SysLogRepository> implements SysLogService {
    @Override
    public Result getHome(Map<String, Object> params) {
        Page<SysLogEntity> entityPage = repository.findAll(SysLogSpec.where(params), JpaPageUtils.getPageRequest(params));
        return Result.ok().putResult(entityPage.getContent()).putTotal(entityPage.getTotalElements());
    }

}
