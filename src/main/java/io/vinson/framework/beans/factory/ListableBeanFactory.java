package io.vinson.framework.beans.factory;

import java.util.Map;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/11
 */
public interface ListableBeanFactory extends BeanFactory {

    <T> Map<String, T> getBeansOfType(Class<T> type);
}
