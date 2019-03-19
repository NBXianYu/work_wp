package com.core.work.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Description: 文件接口
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/18 0018 下午 17:30
 */
public interface DataService {

    /**
     * @Description: 将文件保存到本地
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [multipartFile]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @date 2019/3/19 0019 上午 11:19
     */
    Map<String, Object> saveData(MultipartFile[] multipartFile) throws IOException;

    /**
     * @Description: 下载文件
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [httpResponse, url, fileName]
     * @return void
     * @date 2019/3/19 0019 上午 11:19
     */
    void getData(HttpServletResponse httpResponse, String url, String fileName);
}
