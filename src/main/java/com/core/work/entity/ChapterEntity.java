package com.core.work.entity;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author 吴鹏
 * @Description:
 */
@ApiModel("ChapterEntity实体")
@Entity
@Table(name = "chapter")
@Where(clause = "is_delete=0")
public class ChapterEntity extends AbstractEntity {

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "path", length = 100)
    private String path;

    @Column(name = "count", length = 100)
    private int count;

    @Column(name = "complete", length = 100)
    private boolean complete;

    @Column(name = "download", length = 100)
    private boolean download;

    @Column(name = "tid", length = 100)
    private long tid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }
}
