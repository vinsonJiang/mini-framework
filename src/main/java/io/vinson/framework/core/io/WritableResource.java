package io.vinson.framework.core.io;

import java.io.OutputStream;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2019/1/29
 */
public interface WritableResource extends Resource {

    /**
     * 是否是可写资源
     * @return
     */
    default boolean isWritable() {
        return true;
    }

    /**
     * 返回可写输出流
     * @return
     */
    OutputStream getOutputStream();
}
