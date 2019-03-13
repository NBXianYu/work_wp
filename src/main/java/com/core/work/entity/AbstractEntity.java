package com.core.work.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author 吴鹏
 * @Description: 基础信息
 *
 * @Author
 * @Date 2018/12/20 0020 16:34
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AbstractEntity {

    @Id
    @GenericGenerator(name = "createUUID", strategy = "uuid")
    @GeneratedValue(generator = "createUUID")
    @Column(name = "id", length = 32)
    private String id;

    @CreatedDate
    @Column(name = "gmt_create")
    protected Date gmtCreate;

    @LastModifiedDate
    @Column(name = "gmt_modified")
    protected Date gmtModified;

    @Column(name = "is_delete")
    protected int isDelete = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
