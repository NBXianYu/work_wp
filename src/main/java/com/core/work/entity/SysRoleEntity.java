package com.core.work.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@ApiModel("用户角色实体")
@Entity
@Table(name = "sys_user_role")
@Where(clause = "is_delete=0")
public class SysRoleEntity extends AbstractEntity {

    @Column(name = "权限名", length = 40)
    @ApiModelProperty(name = "name", value = "权限名", dataType = "String")
    private String name;


    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.REMOVE)
    @ApiModelProperty(name = "user_id", value = "关联的用户id", dataType = "String")
    private SysUserEntity sysUserEntity;

    @Column(name = "status", length = 1)
    @ApiModelProperty(name = "status", value = "状态0禁用；1：正常", dataType = "int")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
