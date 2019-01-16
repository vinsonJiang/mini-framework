package io.vinson.framework.beans.factory.config;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2019/1/16
 */
public interface BeanPostProcessor {

    default Object postProcessorBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    default Object postProcessorAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
