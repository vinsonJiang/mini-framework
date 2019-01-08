package io.vinson.framework.beans.factory.support;

import io.vinson.framework.beans.exception.NoSuchBeanDefinitionException;
import io.vinson.framework.beans.factory.BeanFactory;
import io.vinson.framework.beans.factory.ListableBeanFactory;
import io.vinson.framework.beans.factory.config.AbstractAutowireCapableBeanFactory;
import io.vinson.framework.beans.factory.config.BeanDefinition;
import io.vinson.framework.core.util.Assert;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 *
 * @author: jiangweixin
 * @date: 2018/12/25
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ListableBeanFactory, BeanDefinitionRegistry {

//    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    public DefaultListableBeanFactory() {
        super();
    }

    public DefaultListableBeanFactory(BeanFactory parent) {
        super(parent);
    }

    //---------------------------------------------------------------------
    // Implementation of remaining BeanFactory methods
    //---------------------------------------------------------------------

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return getBean(requiredType, (Object[]) null);
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) {
        Assert.notNull(requiredType, "RequiredType must not be null");
        String[] beanNames = getBeanNamesForType(requiredType);
        if(beanNames.length > 1) {

        }
        if(beanNames.length == 1) {
            String beanName = beanNames[0];
            T beanInstance = getBean(beanName, requiredType);
            if(beanInstance == null) {
                throw new NoSuchBeanDefinitionException("No such bean of type: " + requiredType);
            }
            return beanInstance;
        } else if(beanNames.length > 1) {
            return null;
        }

        throw new NoSuchBeanDefinitionException("No such bean of type: " + requiredType);
    }

    //---------------------------------------------------------------------
    // Implementation of remaining ListableBeanFactory methods
    //---------------------------------------------------------------------

    @Override
    public String[] getBeanNamesForType(Class<?> type) {

        return doGetBeanNamesForType(type);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {

        Map<String, T> result = new LinkedHashMap<>();

        return null;
    }


    private String[] doGetBeanNamesForType(Class<?> type) {
        return null;
    }

    //---------------------------------------------------------------------
    // Implementation of remaining BeanDefinitionRegistry methods
    //---------------------------------------------------------------------

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        Assert.notNull(beanName, "BeanName must not be null");
        Assert.notNull(beanDefinition, "BeanDefinition must not be null");

        if(beanDefinition instanceof AbstractBeanDefinition) {

        }
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        beanDefinitionMap.remove(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public int getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }
}
