package com.core.work.service.impl;

import com.core.work.entity.FileEntity;
import com.core.work.exception.BaseException;
import com.core.work.service.DataService;
import com.core.work.service.FileService;
import com.core.work.utils.DateUtils;
import com.core.work.utils.FileAttribute;
import com.core.work.utils.URLEncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Description:
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/18 0018 下午 17:28
 */
@Service
public class DataServiceImpl implements DataService {

    private final FileAttribute fileAttribute;
    private final FileService fileService;

    @Value("${stream.http.multipart.file}")
    private String basePath;

    @Autowired
    public DataServiceImpl(FileAttribute fileAttribute, FileService fileService) {
        this.fileAttribute = fileAttribute;
        this.fileService = fileService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveData(MultipartFile[] multipartFile) throws IOException {
        List<String> originalUrlList = new ArrayList<>();
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        if (multipartFile.length == 0) {
            throw new BaseException("文件上传不能为空");
        }
        for (MultipartFile file : multipartFile) {
            String dateFolder = DateUtils.format(new Date(), "yyyyMMdd");
            String folderUrl;
            if (file.getOriginalFilename().endsWith(".mp4")) {
                folderUrl = fileAttribute.getVideo() + dateFolder;
            } else {
                folderUrl = fileAttribute.getFile() + dateFolder;
            }
            File folder = new File(folderUrl);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            // 回去拼接的文件名,如dkjkstewi32352o34o3oj4n.jpg
            String uuidFileName = fileAttribute.getUUIDFileName(file);
            String originalUrl = folderUrl + "/" + uuidFileName;
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(originalUrl);
            byte[] data = new byte[1024];
            int len;
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
            originalUrlList.add(dateFolder + "/" + uuidFileName);
        }
        outputStream.close();
        inputStream.close();
        Map<String, Object> map = new HashMap<>(5);
        map.put("originalUrl", originalUrlList);

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(multipartFile[0].getOriginalFilename());
        fileEntity.setUrl(originalUrlList.get(0));
        fileService.save(fileEntity);
        return map;
    }

    @Override
    public void getData(HttpServletResponse httpResponse, String url, String fileName) {
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            fileName = URLDecoder.decode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        fileName = fileName.replace("&amp;", "&");
        try {
            httpResponse.setContentType("application/octet-stream");
            // 设置文件名
            httpResponse.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            String folderUrl;

            if (fileName.endsWith(".mp4")) {
                folderUrl = fileAttribute.getVideo();
            } else {
                folderUrl = fileAttribute.getFile();
            }
            fileInputStream = new FileInputStream(folderUrl + url);
            outputStream = httpResponse.getOutputStream();
            byte[] data = new byte[1024];
            int len;
            while ((len = fileInputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
