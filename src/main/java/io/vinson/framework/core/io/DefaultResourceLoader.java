package io.vinson.framework.core.io;

import io.vinson.framework.core.util.Assert;

import java.net.URL;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/21
 */
public class DefaultResourceLoader implements ResourceLoader {

    private ClassLoader classLoader;

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "location must not be null");
        URL url = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(url);
//        try {
//            URL url = new URL(location);
//            return new UrlResource(url);
//        } catch (MalformedURLException e) {
////            throw new FileNotFoundException("文件没找到");
//            e.printStackTrace();
//        }
//        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
