package io.vinson.framework.beans.factory.config;

/**
 * @Description: bean实例的描述定义
 *
 * @author: jiangweixin
 * @date: 2018/12/21
 */
public interface BeanDefinition {

    void setBeanClassName(String beanClassName);

    void getBeanClassName();

    boolean isSingleton();


}
