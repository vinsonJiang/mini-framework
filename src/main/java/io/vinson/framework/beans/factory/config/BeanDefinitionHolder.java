package io.vinson.framework.beans.factory.config;

import io.vinson.framework.core.util.Assert;

/**
 * @Description:
 *
 * @author: jiangweixin
 * @date: 2018/12/28
 */
public class BeanDefinitionHolder {

    private final BeanDefinition beanDefinition;

    private final String beanName;

    public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName) {
        Assert.notNull(beanDefinition, "beanDefinition must not be null");
        Assert.notNull(beanName, "beanName must not be null");
        this.beanDefinition = beanDefinition;
        this.beanName = beanName;
    }

    public BeanDefinition getBeanDefinition() {
        return beanDefinition;
    }

    public String getBeanName() {
        return beanName;
    }
}
