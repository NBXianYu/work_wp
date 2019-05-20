package com.core.modules.sys.entity;


import com.core.modules.web.entity.ComicEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @Author 吴鹏
 * @Description:用户信息实体
 */
@ApiModel("用户信息实体")
@Entity
@Table(name = "sys_user")
@Where(clause = "is_delete=0")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserEntity extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "phone", length = 40)
    @ApiModelProperty(name = "phone", value = "手机号", dataType = "String")
    private String phone;

    @Column(name = "user_name", length = 60)
    @ApiModelProperty(name = "userName", value = "用户名", dataType = "String")
    private String userName;

    @Column(name = "passWord", length = 200)
    @ApiModelProperty(name = "passWord", value = "密码", dataType = "String")
    private String passWord;
    /**
     * 盐 - 后期用于加密密码
     */
    @ApiModelProperty(name = "salt", value = "盐", dataType = "String")
    private String salt;

    @Column(name = "status", length = 1)
    @ApiModelProperty(name = "status", value = "状态0禁用；1：正常", dataType = "int")
    private Integer status = 1;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "comic_user", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "comic_id")})
    private Set<ComicEntity> comicEntitySet;
}
