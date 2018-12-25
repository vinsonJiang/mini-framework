package io.vinson.framework.core.util;

/**
 * @Description: 字符串处理工具类
 * @author: jiangweixin
 * @date: 2018/12/21
 */
public class StringUtils {

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }
}
