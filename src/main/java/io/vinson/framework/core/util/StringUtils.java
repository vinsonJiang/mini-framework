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

    /**
     * 判断是否包含有效字符
     * @param str
     * @return
     */
    public static boolean hasText(String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(String str) {
        for(int i = 0; i < str.length(); i++) {
            if(!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
