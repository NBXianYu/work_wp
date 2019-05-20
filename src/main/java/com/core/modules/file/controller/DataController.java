package com.core.modules.file.controller;

import com.core.modules.sys.controller.AbstractController;
import com.core.modules.sys.service.DataService;
import com.core.modules.sys.service.FileService;
import com.core.common.utils.DateUtils;
import com.core.common.utils.FileAttribute;
import com.core.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @Description: 文件上传下载
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/18 0018 下午 17:29
 */
@Api("文件上传下载")
@RestController
@RequestMapping("/file")
public class DataController extends AbstractController {

    private final DataService dataService;

    private final FileAttribute fileAttribute;

    private final FileService fileService;

    @Autowired
    public DataController(DataService dataService, FileAttribute fileAttribute, FileService fileService) {
        this.dataService = dataService;
        this.fileService = fileService;
        this.fileAttribute = fileAttribute;
    }

    @ApiOperation(value = "单文件上传", notes = "单文件上传")
    @PostMapping(value = "/up_load", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Result upLoad(@RequestParam(value = "multipartFile", name = "multipartFile") MultipartFile[] multipartFile) {
        try {
            Map<String, Object> map = dataService.saveData(multipartFile);
            return Result.ok("资源上传成功").putResult(map);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传异常");
        }
    }

    @ApiOperation(value = "获取文件列表", notes = "获取文件列表")
    @GetMapping(value = "/list")
    public Result list() {
        return Result.ok().putResult(fileService.findAll());
    }

    @ApiOperation(value = "下载文件", notes = "下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "文件名字", value = "fileName", dataType = "String", paramType = "query",required = true),
            @ApiImplicitParam(name = "文件路径（20190311/xxx.png）", value = "url", dataType = "String", paramType = "query",required = true)
    })
    @GetMapping(value = "/down_load")
    public void downLoad(HttpServletResponse httpResponse, String url, String fileName) {
        dataService.getData(httpResponse, url, fileName);
    }

    /**
     * 多文件上传
     *
     * @param files
     * @return
     * @throws IOException
     */
    @PostMapping("/up_load_files")
    @ApiOperation(value = "多文件上传(未完成，禁止使用)", notes = "多文件上传")
    public Result test(@RequestParam("file") List<MultipartFile> files) throws IOException {
        String dateFolder = DateUtils.format(new Date(), "yyyyMMdd");

        List<FileEntity> fileEntities = new ArrayList<>();
        MultipartFile file;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
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
            // D:/resource/20180510/dkjkstewi32352o34o3oj4n.jpg
            String originalUrl = folderUrl + "/" + uuidFileName;
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    Objects.requireNonNull(file.getOriginalFilename());
                    stream = new BufferedOutputStream(new FileOutputStream(originalUrl));
                    stream.write(bytes);
                    stream.close();
                    fileEntities.add(new FileEntity(file.getOriginalFilename(), dateFolder + "/" + uuidFileName,
                            file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1)));
                } catch (Exception e) {
                    if (stream != null) {
                        stream.close();
                        stream = null;
                    }
                    return Result.error("第 " + i + " 个文件上传有错误" + e.getMessage());
                }
            } else {
                return Result.error("第 " + i + " 个文件为空");
            }
        }
        return Result.ok("上传成功").put("results", fileEntities);
    }

    /**
     * 单文件上传
     */
    @PostMapping("/up_load_file")
    public Result upload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
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
            String uuidFileName = fileAttribute.getUUIDFileName(file);
            String originalUrl = folderUrl + "/" + uuidFileName;
            List<FileEntity> fileEntities = new ArrayList<>();
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(originalUrl));
                out.write(file.getBytes());
                out.flush();
                out.close();
                fileEntities.add(new FileEntity(file.getOriginalFilename(), dateFolder + "/" + uuidFileName,
                        file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1)));
                return Result.ok("上传成功").put("results", fileEntities);
            } catch (IOException e) {
                e.printStackTrace();
                return Result.error("上传失败");
            }
        } else {
            return Result.error("上传文件不能为空");
        }
    }

    class FileEntity implements Serializable {
        private static final long serialVersionUID = 5656187379697418144L;

        FileEntity(String fileName, String fileUrl, String filenameExtension) {

            this.fileName = fileName;
            this.fileUrl = fileUrl;
            this.filenameExtension = filenameExtension;
        }
        // 文件id
        private String id;
        // 文件名
        private String fileName;
        // 文件别名
        private String name = null;
        // 文件地址
        private String fileUrl;
        // 文件格式、文件扩展名、后缀
        private String filenameExtension = "jpg";
        // 创建时间
        private Date gmtCreate = new Date();
        // 上传人
        private String createUserName = null;
        // 上传人Id
        private String createUserId = null;
        // 文件说明
        private String remark = null;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getFilenameExtension() {
            return filenameExtension;
        }

        public void setFilenameExtension(String filenameExtension) {
            this.filenameExtension = filenameExtension;
        }

        public Date getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Date gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

}
