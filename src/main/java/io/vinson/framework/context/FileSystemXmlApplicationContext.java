package io.vinson.framework.context;


import io.vinson.framework.beans.factory.ListableBeanFactory;
import io.vinson.framework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Description:
 *
 * @author: jiangweixin
 * @date: 2018/12/25
 */
public class FileSystemXmlApplicationContext extends AbstractXmlApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    public FileSystemXmlApplicationContext() {

    }

    public FileSystemXmlApplicationContext(ApplicationContext parent) {
        super(parent);
    }

    public FileSystemXmlApplicationContext(String... configLocations) {
        this(configLocations, null, true);
    }

    public FileSystemXmlApplicationContext(String[] configLocations, ApplicationContext parent) {
        this(configLocations, parent, true);
    }

    public FileSystemXmlApplicationContext(String[] configLocations, boolean refresh) {
        this(configLocations, null, refresh);
    }

    public FileSystemXmlApplicationContext(String[] configLocations, ApplicationContext parent, boolean refresh) {
        super(parent);
        setConfigLocations(configLocations);
        if(refresh) {
            refresh();
        }
    }

    @Override
    protected final void refreshBeanFactory() {
        DefaultListableBeanFactory tempBeanFactory = createBeanFactory();
        loadBeanDefinitions(tempBeanFactory);
        this.beanFactory = tempBeanFactory;
    }

    @Override
    public  ListableBeanFactory getBeanFactory() {
        if(this.beanFactory == null) {
            throw new IllegalStateException("beanFactory is null");
        }
        return this.beanFactory;
    }
}
