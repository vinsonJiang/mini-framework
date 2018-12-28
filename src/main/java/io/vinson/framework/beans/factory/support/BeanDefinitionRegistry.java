package io.vinson.framework.beans.factory.support;

import io.vinson.framework.beans.factory.config.BeanDefinition;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/28
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册bean
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 移除bean
     * @param beanName
     */
    void removeBeanDefinition(String beanName);

    BeanDefinition getBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();

    int getBeanDefinitionCount();

}
