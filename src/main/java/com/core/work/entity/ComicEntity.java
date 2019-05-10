package com.core.work.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/***
 * @Author: 吴鹏
 * @Description: 漫画信息实体类
 */
@ApiModel("漫画信息实体类")
@Entity
@Table(name = "comic")
@Where(clause = "is_delete=0")
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
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "comicEntityList")
    private Set<SysUserEntity> sysUserEntityList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Integer getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }

    public Set<SysUserEntity> getSysUserEntityList() {
        return sysUserEntityList;
    }

    public void setSysUserEntityList(Set<SysUserEntity> sysUserEntityList) {
        this.sysUserEntityList = sysUserEntityList;
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
