package com.core.modules.sys.entity.vo;


import com.core.modules.web.entity.ComicEntity;
import com.core.common.utils.EntityCopyUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/***
 * @Author: 吴鹏
 * @Description: 漫画信息视图
 */
@ApiModel("漫画信息视图")
@Data
public class ComicVo {

    @ApiModelProperty(name = "title", value = "漫画标题", dataType = "String")
    private String title;

    @ApiModelProperty(name = "description", value = "漫画描述", dataType = "String")
    private String description;

    @ApiModelProperty(name = "press", value = "出版社", dataType = "String")
    private String press;

    @ApiModelProperty(name = "collectNumber", value = "收藏人数", dataType = "Integer")
    private Integer collectNumber = 0;

    @ApiModelProperty(name = "evaluate", value = "评价：1-5等级", dataType = "Integer")
    private Integer evaluate = 1;

    private String id;

    private Date gmtCreate;

    private Date gmtModified;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * @Author: 吴鹏
     * @Description: home展示需要的字段
     */
    public static ComicVo getHomeVoByEntity(ComicEntity comicEntity) {
        ComicVo comicVo = new ComicVo();
        EntityCopyUtil.copyData(comicEntity, comicVo);
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
