package io.vinson.framework.beans.factory.support;

import io.vinson.framework.core.io.DefaultResourceLoader;
import io.vinson.framework.core.io.Resource;
import io.vinson.framework.core.io.ResourceLoader;
import io.vinson.framework.core.io.ResourceResolver;
import io.vinson.framework.core.util.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/29
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    protected final Log logger = LogFactory.getLog(getClass());

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    private ClassLoader classLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        Assert.notNull(registry, "BeanDefinitionRegistry must not be null");
        this.registry = registry;

        if(this.registry instanceof ResourceLoader) {
            this.resourceLoader = (ResourceLoader) this.registry;
        } else {
            this.resourceLoader = new DefaultResourceLoader();
        }
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public int loadBeanDefinitions(Resource[] resources) {
        Assert.notNull(resources, "Resources must not be null");
        int count = 0;
        for (Resource resource : resources) {
            count += loadBeanDefinitions(resource);
        }
        return count;
    }

    @Override
    public int loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        if(resourceLoader == null) {
            throw new IllegalStateException("ResourceLoader is null");
        }
        if(resourceLoader instanceof ResourceResolver) {
            Resource[] resources = ((ResourceResolver) resourceLoader).getResources(location);
            int count = loadBeanDefinitions(resources);
            return count;
        } else {
            Resource resource = resourceLoader.getResource(location);
            int count = loadBeanDefinitions(resource);
            return count;
        }
    }

    @Override
    public int loadBeanDefinitions(String[] locations) {
        Assert.notNull(locations, "Locations must not be null");
        int count = 0;
        for (String location : locations) {
            count += loadBeanDefinitions(location);
        }
        return count;
    }
}
