package io.vinson.framework.core.io;

import io.vinson.framework.core.util.Assert;

import java.net.URL;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/21
 */
public class DefaultResourceLoader implements ResourceLoader {


    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "location 不能为空");
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
}
