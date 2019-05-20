package com.core.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @Description:
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/18 0018 下午 17:29
 */
@Component
public class FileAttribute {
    @Value("${stream.http.multipart.file}")
    private String file;

    @Value("${stream.http.multipart.video}")
    private String video;

    /**
     * 生成新的唯一文件名
     */
    public String getUUIDFileName(MultipartFile file) {
        // 生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 文件名后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        return uuid + suffix;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
