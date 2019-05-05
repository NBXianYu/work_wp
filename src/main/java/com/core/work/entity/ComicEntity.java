package com.core.work.entity;


import com.core.work.entity.vo.ComicVo;
import com.core.work.utils.EntityCopyUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

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
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "comic_user", joinColumns = {@JoinColumn(name = "comic_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<SysUserEntity> sysUserEntityList;

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

    public List<SysUserEntity> getSysUserEntityList() {
        return sysUserEntityList;
    }

    public void setSysUserEntityList(List<SysUserEntity> sysUserEntityList) {
        this.sysUserEntityList = sysUserEntityList;
    }

    @Override
    public String toString() {
        return "ComicEntity{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", press='" + press + '\'' +
                ", collectNumber=" + collectNumber +
                ", evaluate=" + evaluate +
                ", sysUserEntityList=" + sysUserEntityList +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", isDelete=" + isDelete +
                '}';
    }

    /**
     * @Author: 吴鹏
     * @Description: home展示需要的字段
     */
    public static ComicVo getHomeVoByEntity(ComicEntity comicEntity) {
        ComicVo comicVo = new ComicVo();
        EntityCopyUtil.copyData(comicEntity, comicVo);
        System.out.println(comicEntity);
        System.out.println(comicVo);
        return comicVo;
    }

    /***
     * @Author: 吴鹏
     * @Description: detail详情页展示需要的字段
     */
    public static ComicVo getDetailVoByEntity(ComicEntity comicEntity) {
        ComicVo comicVo = new ComicVo();
        EntityCopyUtil.copyData(comicEntity, comicVo);
        return comicVo;
    }
}
