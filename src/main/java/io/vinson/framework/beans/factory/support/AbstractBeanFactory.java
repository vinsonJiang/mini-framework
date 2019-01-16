package io.vinson.framework.beans.factory.support;

import io.vinson.framework.beans.exception.BeanCreationException;
import io.vinson.framework.beans.factory.BeanFactory;
import io.vinson.framework.beans.factory.config.BeanDefinition;
import io.vinson.framework.beans.factory.config.BeanPostProcessor;
import io.vinson.framework.core.util.Assert;
import io.vinson.framework.core.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/11
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private BeanFactory parentBeanFactory;

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    protected final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public AbstractBeanFactory() {

    }

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

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        Object bean = beanDefinition;
        if(beanDefinition != null) {
            if(beanDefinition instanceof RootBeanDefinition) {
                try {
                    Object object = ((RootBeanDefinition) beanDefinition).getBeanClass().newInstance();
                    return (T) object;
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return (T) bean;
        }
        if(requiredType != null) {
//            Object instance = createBean(name, null, null);
            Object instance = createBean(requiredType);
            return (T) instance;
        }

        throw new BeanCreationException("beanName:[" + beanName + "] can not be created");

//        Assert.notNull(beanDefinition, "beanName:[" + beanName + "] is not defined");
//
//        return (T)bean;
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


    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        Assert.notNull(beanPostProcessor, "BeanPostProcessor must not be null");

        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public int getBeanPostProcessorCount() {
        return this.beanPostProcessors.size();
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    //---------------------------------------------------------------------
    // Abstract methods to be implemented by subclasses
    //---------------------------------------------------------------------

    public abstract <T> T createBean(Class<T> beanClass);

    protected abstract Object createBean(String beanName, RootBeanDefinition rootBean, Object[] args);
}
