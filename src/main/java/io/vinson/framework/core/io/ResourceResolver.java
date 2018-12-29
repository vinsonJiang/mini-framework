package io.vinson.framework.core.io;

import io.vinson.framework.core.util.Assert;

/**
 * @Description:
 * 资源解析器
 * @author: jiangweixin
 * @date: 2018/12/29
 */
public class ResourceResolver implements ResourceLoader {

    private final ResourceLoader resourceLoader;

    public ResourceResolver() {
        resourceLoader = new DefaultResourceLoader();
    }

    public ResourceResolver(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    @Override
    public Resource getResource(String location) {
        return resourceLoader.getResource(location);
    }

    @Override
    public ClassLoader getClassLoader() {
        return resourceLoader.getClassLoader();
    }

    public Resource[] getResources(String location) {
        Assert.notNull(location, "Location must not be null");

        return new Resource[] { getResourceLoader().getResource(location) };
    }
}
