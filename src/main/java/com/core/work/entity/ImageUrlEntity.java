package com.core.work.entity;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author 吴鹏
 * @Description:
 */
@ApiModel("ImageUrlEntity实体")
@Entity
@Table(name = "image_url")
@Where(clause = "is_delete=0")
public class ImageUrlEntity extends AbstractEntity {

    private static AtomicInteger count = new AtomicInteger(0);

    public static final int STATE_NULL = 0;
    public static final int STATE_PAGE_1 = 1;
    public static final int STATE_PAGE_2 = 2;

    /**
     * @Description: 章节的第几页
     */
    @Column(name = "num")
    private int num;

    @Column(name = "urls", length = 40)
    private String urls;

    /***
     * @Description: 所属章节
     */
    @Column(name = "chapter", length = 40)
    private String chapter;

    /***
     * @Description: 切图时表示状态 这里可以改为编号 比如长图可以切为多张方便加载
     */
    @Column(name = "state")
    private int state;

    /**
     * @Description: 图片高度
     */
    @Column(name = "height")
    private int height;

    /**
     * @Description: 图片宽度
     */
    @Column(name = "width")
    private int width;

    /**
     * @Description: 懒加载
     */
    @Column(name = "lazy")
    private boolean lazy;

    /**
     * @Description: 正在懒加载
     */
    @Column(name = "loading")
    private boolean loading;

    /**
     * @Description: 图片显示成功
     */
    @Column(name = "success")
    private boolean success;

    /**
     * @Description: 下载的图片
     */
    @Column(name = "download")
    private boolean download;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isLazy() {
        return lazy;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }
}
