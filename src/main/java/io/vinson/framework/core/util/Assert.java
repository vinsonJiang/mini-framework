package io.vinson.framework.core.util;

/**
 * @Description: 断言工具类
 * @author: jiangweixin
 * @date: 2018/12/21
 */
public class Assert {

    public static void notNull(Object object, String message) {
        if(object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
