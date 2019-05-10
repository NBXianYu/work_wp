package com.core.work.entity.vo;


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
 * @Description: 漫画信息视图
 */
@ApiModel("漫画信息视图")
public class MiniComicVo extends AbstractEntity {

    private String id;
    private int source;
    private String cid;
    private String title;
    private String cover;
    private Boolean finish;
    private boolean highlight;
    private boolean local;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public static MiniComicVo getMiniComicVoByEntity(ComicEntity comicEntity) {
        MiniComicVo comicVo = new MiniComicVo();
        EntityCopyUtil.copyData(comicEntity, comicVo);
        // 继承过来的属性不会copy，需要手动赋值一下
        comicVo.setId(comicEntity.getId());
        return comicVo;
    }
}
