package io.vinson.framework.beans.factory.config;

/**
 * @Description: bean实例的具体描述定义
 *
 * @author: jiangweixin
 * @date: 2018/12/21
 */
public interface BeanDefinition {

    /**
     * 修改beanClass
     * @param beanClassName
     */
    void setBeanClassName(String beanClassName);

    /**
     * 获取beanClassName
     */
    String getBeanClassName();

    /**
     * 是否单例
     * @return
     */
    boolean isSingleton();

    /**
     * 是否是abstract的
     * @return
     */
    boolean isAbstract();

    /**
     * 返回bean的描述
     * @return
     */
    String getDescription();
}
