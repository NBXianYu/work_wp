package com.core.work.entity.form;


import com.core.work.entity.AbstractEntity;
import com.core.work.entity.ComicEntity;
import com.core.work.entity.SysUserEntity;
import com.core.work.utils.EntityCopyUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/***
 * @Author: 吴鹏
 * @Description: 漫画信息表单
 */
@ApiModel("漫画信息表单")
public class ComicForm extends AbstractEntity {

    @ApiModelProperty(name = "id", value = "id", dataType = "String")
    private String id;

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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

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

    /***
     * @Author: 吴鹏
     * @Description: 根据表单信息返回Entity
     */
    public static ComicEntity getComicEntityByForm (ComicForm comicForm, ComicEntity comicEntity) {
        if (comicEntity == null) {
            comicEntity = new ComicEntity();
        }
        EntityCopyUtil.copyData(comicForm, comicEntity);
        return comicEntity;
    }
}
