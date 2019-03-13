package com.core.work.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author 吴鹏
 * @Date Created in 下午 18:27 2019/1/28 0028
 * @Email 694798354@qq.com
 * @Description:
 */
@Entity
@Table(name = "sys_user_token")
public class SysUserTokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "createUUID", strategy = "uuid")
    @GeneratedValue(generator = "createUUID")
    @Column(name = "token_id", length = 32)
    @ApiModelProperty(name = "uuid", value = "系统用户token id", dataType = "String")
    private String uuid;


    @Column(name = "user_id",length = 32)
    @ApiModelProperty(name = "userId", value = "用户id", dataType = "String")
    private String userId;

    @Column(name = "token")
    @ApiModelProperty(name = "token", value = "token", dataType = "String")
    private String token;

    @Column(name = "expire_time")
    @ApiModelProperty(name = "expireTime", value = "过期时间", dataType = "date")
    private Date expireTime;

    @Column(name = "update_time")
    @ApiModelProperty(name = "updateTime", value = "更新时间", dataType = "date")
    private Date updateTime;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
