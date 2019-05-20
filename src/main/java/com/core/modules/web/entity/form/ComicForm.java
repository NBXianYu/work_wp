package com.core.modules.web.entity.form;


import com.core.common.validator.group.AddGroup;
import com.core.common.validator.group.UpdateGroup;
import com.core.modules.web.entity.ComicEntity;
import com.core.common.utils.EntityCopyUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/***
 * @Author: 吴鹏
 * @Description: 漫画信息表单
 */
@ApiModel("漫画信息表单")
@Data
public class ComicForm {

    @ApiModelProperty(name = "id", value = "id", dataType = "String")
    private String id;

    @NotBlank(message = "漫画标题不能为空", groups = {AddGroup.class, UpdateGroup.class})
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
