package com.core.modules.job.entity;

import com.core.modules.sys.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@ApiModel("定时器实体类")
@Entity
@Table(name = "shedule_job")
@Where(clause = "is_delete=0")
@Data
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

}
