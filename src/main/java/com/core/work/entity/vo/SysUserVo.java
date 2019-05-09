package com.core.work.entity.vo;


import com.core.work.entity.SysUserEntity;
import com.core.work.utils.EntityCopyUtil;
import com.core.work.validation.CheckDataUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 吴鹏
 * @Description:用户信息VO
 */
public class SysUserVo{

    private String id;

    private String phone;

    private String userName;

    private Integer status;

    private List<ComicVo> comicVoList;

    private Date gmtCreate;

    private Date gmtModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ComicVo> getComicVoList() {
        return comicVoList;
    }

    public void setComicVoList(List<ComicVo> comicVoList) {
        this.comicVoList = comicVoList;
    }


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
        if (!CheckDataUtils.listIsNull(sysUserEntity.getComicEntityList())) {
            List<ComicVo> comicVoList = new ArrayList<>();
            sysUserEntity.getComicEntityList().stream().map(value -> ComicVo.getDetailVoByEntity(value)).forEach(comicVoList::add);
            sysUserVo.setComicVoList(comicVoList);
        }
        return sysUserVo;
    }
}
