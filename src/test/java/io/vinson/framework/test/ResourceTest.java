package io.vinson.framework.test;

import io.vinson.framework.core.io.DefaultResourceLoader;
import io.vinson.framework.core.io.Resource;
import io.vinson.framework.core.io.ResourceLoader;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/25
 */
public class ResourceTest {

    @Test
    public void test1() {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("framework.xml");
        try {
            InputStream inputStream = resource.getInputStream();
            System.out.println(resource.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
