package com.core.work.xss;

import com.core.work.exception.BaseException;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: SQL过滤
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/13 0013 下午 15:05
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     */
    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        // 去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");
        // 转换成小写
        str = str.toLowerCase();
        // 非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};
        // 判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.contains(keyword)) {
                throw new BaseException("包含非法字符");
            }
        }

        return str;
    }
}
