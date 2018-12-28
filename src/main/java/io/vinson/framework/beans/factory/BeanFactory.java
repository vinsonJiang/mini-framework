package io.vinson.framework.beans.factory;

/**
 * @Description:
 *
 * @author: jiangweixin
 * @date: 2018/12/10
 */
public interface BeanFactory {

    /**
     * 根据名字返回一个实例
     * @param name
     * @return
     */
    Object getBean(String name);

    <T> T getBean(String name, Class<T> requiredType);

    <T> T getBean(Class<T> requiredType);

    <T> T getBean(Class<T> requiredType, Object... args);

    boolean containsBean(String name);

    boolean isSingleton(String name);
}
