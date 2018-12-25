package io.vinson.framework.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @Description: 对资源描述符的抽象
 *
 * @author: jiangweixin
 * @date: 2018/12/20
 */
public interface Resource extends InputStreamSource {

    /**
     * 判断资源是否存在硬盘
     * @return
     */
    boolean isExist();


    default boolean isReadable() {
        return true;
    }

    default boolean isOpen() {
        return false;
    }

    URL getURL() throws IOException;

    File getFile() throws IOException;

    long getContentLength() throws IOException;

    String getDescription();

}
