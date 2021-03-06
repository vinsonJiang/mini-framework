package io.vinson.framework.context;

import io.vinson.framework.beans.factory.support.DefaultListableBeanFactory;
import io.vinson.framework.beans.factory.support.XmlBeanDefinitionReader;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2018/12/29
 */
public abstract class AbstractXmlApplicationContext extends AbstractApplicationContext {

    public AbstractXmlApplicationContext() {
    }

    public AbstractXmlApplicationContext(ApplicationContext parent) {
        super(parent);
    }

    public void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(getConfigLocations());
    }
}
