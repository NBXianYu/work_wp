package com.core.modules.file.service.impl;

import com.core.modules.file.repository.FileRepository;
import com.core.modules.file.entity.FileEntity;
import com.core.modules.sys.service.FileService;
import com.core.modules.sys.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author 吴鹏
 * @Date Created in 下午 16:31 2019/3/19 0019
 * @Email 694798354@qq.com
 * @Description:
 */
@Service
public class FileServiceImpl extends BaseServiceImpl<FileEntity, FileRepository> implements FileService {
}
