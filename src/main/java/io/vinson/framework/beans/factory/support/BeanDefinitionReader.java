package io.vinson.framework.beans.factory.support;

import io.vinson.framework.core.io.Resource;
import io.vinson.framework.core.io.ResourceLoader;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/29
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();


    ResourceLoader getResourceLoader();

    ClassLoader getClassLoader();


    int loadBeanDefinitions(Resource resource);

    int loadBeanDefinitions(Resource[] resources);

    int loadBeanDefinitions(String location);

    int loadBeanDefinitions(String[] locations);
}
