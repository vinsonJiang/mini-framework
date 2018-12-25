package io.vinson.framework.core.io;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/21
 */
public interface ResourceLoader {

    /**
     * 返回指定资源的句柄
     * @param location
     * @return
     */
    Resource getResource(String location);
}
