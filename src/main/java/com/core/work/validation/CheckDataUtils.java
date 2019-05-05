package com.core.work.validation;

import com.core.work.exception.BaseException;
import org.apache.commons.lang3.StringUtils;

/***
 * @Author: 吴鹏
 * @Description: 数据校验工具类
 */
public abstract class CheckDataUtils {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BaseException(message);
        }
    }
}
