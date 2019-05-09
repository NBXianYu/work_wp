package com.core.work.entity;


import com.core.work.entity.vo.MiniComicVo;
import com.core.work.utils.EntityCopyUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
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

    @Column(name = "source")
    private int source;
    @Column(name = "cid", length = 40)
    private String cid;
    @Column(name = "title", length = 40)
    private String title;
    @Column(name = "cover", length = 140)
    private String cover;
    @Column(name = "highlight")
    private boolean highlight;
    @Column(name = "local")
    private boolean local;

    @Column(name = "update_comic")
    private String update;

    @Column(name = "finish")
    private Boolean finish;
    @Column(name = "favorite")
    private Long favorite;
    @Column(name = "history")
    private Long history;
    @Column(name = "download")
    private Long download;
    @Column(name = "last", length = 140)
    private String last;
    @Column(name = "page")
    private Integer page;
    @Column(name = "chapter", length = 140)
    private String chapter;
    @Column(name = "intro", length = 140)
    private String intro;
    @Column(name = "author", length = 140)
    private String author;

    @JsonBackReference
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "comicEntityList")
    private List<SysUserEntity> sysUserEntityList;

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

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Long getFavorite() {
        return favorite;
    }

    public void setFavorite(Long favorite) {
        this.favorite = favorite;
    }

    public Long getHistory() {
        return history;
    }

    public void setHistory(Long history) {
        this.history = history;
    }

    public Long getDownload() {
        return download;
    }

    public void setDownload(Long download) {
        this.download = download;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<SysUserEntity> getSysUserEntityList() {
        return sysUserEntityList;
    }

    public void setSysUserEntityList(List<SysUserEntity> sysUserEntityList) {
        this.sysUserEntityList = sysUserEntityList;
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

    public static MiniComicVo getMiniComicVoByEntity(ComicEntity comicEntity) {
        MiniComicVo comicVo = new MiniComicVo();
        EntityCopyUtil.copyData(comicEntity, comicVo);
        // 继承过来的属性不会copy，需要手动赋值一下
        comicVo.setId(comicEntity.getId());
        return comicVo;
    }

}
