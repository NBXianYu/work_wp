package com.core.work.entity;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author 吴鹏
 * @Description:
 */
@ApiModel("SourceEntity实体")
@Entity
@Table(name = "source")
@Where(clause = "is_delete=0")
public class SourceEntity extends AbstractEntity {

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "type")
    private int type;

    @Column(name = "enable")
    private boolean enable;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
