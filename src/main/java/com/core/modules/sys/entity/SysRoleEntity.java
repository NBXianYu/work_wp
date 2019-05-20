package com.core.modules.sys.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@ApiModel("用户角色实体")
@Entity
@Table(name = "sys_user_role")
@Where(clause = "is_delete=0")
@Data
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

}
