package com.core.modules.web.entity;


import com.core.modules.sys.entity.AbstractEntity;
import com.core.modules.sys.entity.SysUserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

/***
 * @Author: 吴鹏
 * @Description: 漫画信息实体类
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("漫画信息实体类")
@Entity
@Table(name = "comic")
@Where(clause = "is_delete=0")
@Data
public class ComicEntity extends AbstractEntity {

    @Column(name = "title", length = 40)
    @ApiModelProperty(name = "title", value = "漫画标题", dataType = "String")
    private String title;

    @Column(name = "description", length = 200)
    @ApiModelProperty(name = "description", value = "漫画描述", dataType = "String")
    private String description;

    @Column(name = "press", length = 100)
    @ApiModelProperty(name = "press", value = "出版社", dataType = "String")
    private String press;

    @Column(name = "collectNumber")
    @ApiModelProperty(name = "collectNumber", value = "收藏人数", dataType = "Integer")
    private Integer collectNumber = 0;

    @Column(name = "evaluate")
    @ApiModelProperty(name = "evaluate", value = "评价：1-5等级", dataType = "Integer")
    private Integer evaluate = 1;

    @JsonBackReference
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "comicEntitySet")
    private Set<SysUserEntity> sysUserEntitySet;

}
