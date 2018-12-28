package io.vinson.framework.beans.factory.support;

import io.vinson.framework.beans.factory.BeanFactory;
import io.vinson.framework.beans.factory.config.BeanDefinition;
import io.vinson.framework.core.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/11
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private BeanFactory parentBeanFactory;

    private ClassLoader beanClassLoader;

    private final Map<String, RootBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    //---------------------------------------------------------------------
    // Implementation of BeanFactory interface
    //---------------------------------------------------------------------

    @Override
    public Object getBean(String name) {
        return doGetBean(name, null);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return doGetBean(name, requiredType);
    }

    @Override
    public boolean containsBean(String name) {
        return beanDefinitionMap.containsKey(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    protected <T> T doGetBean(final String name, final Class<T> requiredType) {

        String beanName = name;

        BeanFactory tempParentBeanFactory = getParentBeanFactory();

        Object bean;
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        Assert.notNull(beanDefinition, "beanName:[" + beanName + "] is not defined");
        bean = beanDefinition;
        return (T)bean;
    }

    public void setParentBeanFactory(BeanFactory parentBeanFactory) {
        this.parentBeanFactory = parentBeanFactory;
    }

    public BeanFactory getParentBeanFactory() {
        return this.parentBeanFactory;
    }


    protected Class<?> resolveBeanClass(final String beanName, final RootBeanDefinition rootBeanDefinition) throws ClassNotFoundException {

        if(rootBeanDefinition.hasBeanClass()) {
            return rootBeanDefinition.getBeanClass();
        } else {
            return doResolveBeanClass(rootBeanDefinition);
        }
    }

    private Class<?> doResolveBeanClass(RootBeanDefinition rootBeanDefinition) throws ClassNotFoundException {

        ClassLoader beanClassLoader = this.beanClassLoader;

        return rootBeanDefinition.resolveBeanClass(beanClassLoader);

    }

}
