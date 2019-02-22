package com.core.work.entity;

import com.core.work.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@ApiModel("定时器实体类")
@Entity
@Table(name = "shedule_job")
@Where(clause = "is_delete=0")
public class SheduleJobEntity extends AbstractEntity {

    @Column(name = "name",length = 100)
    @ApiModelProperty(name = "name", value = "job名", dataType = "String")
    private String name;

    @Column(name = "start_time")
    @ApiModelProperty(name = "startTime", value = "开始时间", dataType = "Date")
    private Date startTime;

    @Column(name = "type",length = 1)
    @ApiModelProperty(name = "type", value = "定时器类型：1日常工作；2：计划实例;3:任务实例开始定时器；4：任务实列截止定时器", dataType = "int")
    private Integer type;

    @Column(name = "s_key")
    @ApiModelProperty(name = "key", value = "存储定时器的key", dataType = "String")
    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
