package io.vinson.framework.beans.factory.support;

import io.vinson.framework.beans.factory.ListableBeanFactory;

import java.util.Map;

/**
 * @Description:
 *
 * @author: jiangweixin
 * @date: 2018/12/25
 */
public class DefaultListableBeanFactory implements ListableBeanFactory {
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return null;
    }

    @Override
    public Object getBean(String name) {
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }
}
