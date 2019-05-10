package com.core.work.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    private Set<ComicEntity> comicEntityList;

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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<ComicEntity> getComicEntityList() {
        return comicEntityList;
    }

    public void setComicEntityList(Set<ComicEntity> comicEntityList) {
        this.comicEntityList = comicEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComicEntity that = (ComicEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
