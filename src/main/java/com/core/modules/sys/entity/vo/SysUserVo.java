package com.core.modules.sys.entity.vo;


import com.core.modules.sys.entity.SysUserEntity;
import com.core.common.utils.EntityCopyUtil;
import com.core.common.validator.CheckDataUtils;
import com.core.modules.web.entity.vo.ComicVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 吴鹏
 * @Description:用户信息VO
 */
@Data
public class SysUserVo{

    private String id;

    private String phone;

    private String userName;

    private Integer status;

    private List<ComicVo> comicVoList;

    private Date gmtCreate;

    private Date gmtModified;

    /**
     * @Author: 吴鹏
     * @Description: home展示需要的字段
     */
    public static SysUserVo getHomeVoByEntity(SysUserEntity sysUserEntity) {
        SysUserVo sysUserVo = new SysUserVo();
        EntityCopyUtil.copyData(sysUserEntity, sysUserVo);
        return sysUserVo;
    }

    /***
     * @Author: 吴鹏
     * @Description: detail详情页展示需要的字段
     */
    public static SysUserVo getDetailVoByEntity(SysUserEntity sysUserEntity) {
        SysUserVo sysUserVo = new SysUserVo();
        EntityCopyUtil.copyData(sysUserEntity, sysUserVo);
        // 关联的漫画详情
        if (!CheckDataUtils.listIsNull(sysUserEntity.getComicEntitySet())) {
            List<ComicVo> comicVoList = new ArrayList<>();
            sysUserEntity.getComicEntitySet().stream().map(value -> ComicVo.getDetailVoByEntity(value)).forEach(comicVoList::add);
            sysUserVo.setComicVoList(comicVoList);
        }
        return sysUserVo;
    }
}
