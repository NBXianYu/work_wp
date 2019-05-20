package com.core.common.validator;

import com.core.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

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

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new BaseException(message);
        }
    }

    public static Boolean listIsNull (Collection list) {
        if (list == null || list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
