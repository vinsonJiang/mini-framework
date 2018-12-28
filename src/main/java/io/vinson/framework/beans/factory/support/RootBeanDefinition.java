package io.vinson.framework.beans.factory.support;

import io.vinson.framework.beans.factory.config.BeanDefinitionHolder;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/28
 */
public class RootBeanDefinition extends AbstractBeanDefinition {

    private BeanDefinitionHolder beanDefinitionHolder;


    public RootBeanDefinition(Class<?> beanClass) {
        setBeanClass(beanClass);
    }

    public RootBeanDefinition(RootBeanDefinition beanDefinition) {
        this.beanDefinitionHolder = beanDefinition.beanDefinitionHolder;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public AbstractBeanDefinition cloneBeanDefinition() {
        return new RootBeanDefinition(this);
    }
}
