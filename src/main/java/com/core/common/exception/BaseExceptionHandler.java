package com.core.common.exception;

import com.core.common.utils.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * @Description: 异常处理器
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/19 0019 下午 14:09
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @description : 处理自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public Result handleRRException(BaseException e) {
        Result result = new Result();
        result.put("code", e.getCode());
        result.put("msg", e.getMessage());
        return result;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e) {
        logger.error(e.getMessage(), e);
        return Result.error("没有权限，请联系管理员授权");
    }


    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.error();
    }
}
