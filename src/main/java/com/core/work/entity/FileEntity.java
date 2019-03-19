package com.core.work.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author 吴鹏
 * @Date Created in 下午 16:25 2019/3/19 0019
 * @Email 694798354@qq.com
 * @Description:
 */
@Entity
@Table(name = "file")
@ApiModel("文件实体类")
public class FileEntity extends AbstractEntity {

    @Column(name = "fileName", length = 40)
    @ApiModelProperty(name = "fileName", value = "文件名", dataType = "String")
    private String fileName;

    @Column(name = "url", length = 60)
    @ApiModelProperty(name = "url", value = "文件路径", dataType = "String")
    private String url;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
