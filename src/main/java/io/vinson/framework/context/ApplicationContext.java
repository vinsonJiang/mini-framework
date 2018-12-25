package io.vinson.framework.context;

import io.vinson.framework.beans.factory.ListableBeanFactory;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/20
 */
public interface ApplicationContext extends ListableBeanFactory {

    String getId();

    String getName();

    long getStartupDate();

    ApplicationContext getParent();
}
