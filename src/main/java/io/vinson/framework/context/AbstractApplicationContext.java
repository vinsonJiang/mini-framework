package io.vinson.framework.context;

import io.vinson.framework.beans.factory.ListableBeanFactory;
import io.vinson.framework.beans.factory.support.DefaultListableBeanFactory;
import io.vinson.framework.core.io.DefaultResourceLoader;
import io.vinson.framework.core.io.ResourceLoader;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 *
 * @author: jiangweixin
 * @date: 2018/12/20
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ApplicationContext, Lifecycle {

    /** 父容器 */
    private ApplicationContext parent;

    /** id */
    private String id;

    /** name */
    private String name;

    /** 启动时间(ms) */
    private long startupDate;

    /** refresh 和 destory 的监控锁 */
    private final Object startupShutdownMonitor = new Object();

    private ResourceLoader resourceLoader;

    private Set<ApplicationEvent> applicationEvents;

//    private DefaultListableBeanFactory beanFactory;

    private String[] configLocations;

    public AbstractApplicationContext() {
        resourceLoader  = new DefaultResourceLoader();
    }

    public AbstractApplicationContext(ApplicationContext parent) {
        this();
        setParent(parent);
    }

    @Override
    public ApplicationContext getParent() {
        return parent;
    }

    public void setParent(ApplicationContext parent) {
        this.parent = parent;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getStartupDate() {
        return startupDate;
    }

    public String[] getConfigLocations() {
        return configLocations;
    }

    public void setConfigLocations(String[] configLocations) {
        if(configLocations != null) {
            if(configLocations.length <= 0) {
                throw new IllegalStateException("configLocations must not be null");
            }
            this.configLocations = new String[configLocations.length];
            for (int i = 0; i < configLocations.length; i++) {
                this.configLocations[i] = configLocations[i].trim();
            }
        } else {
            this.configLocations = null;
        }
    }

    public void refresh() {

        // 0.刷新前准备工作
        prepareRefresh();
        
        // 1.创建BeanFactory
        ListableBeanFactory beanFactory = obtainBeanFactory();
        prepareBeanFactory(beanFactory);

        // 2.注册感兴趣的事件

        // 3.创建Bean实例对象

        // 4.触发被监听的事件

    }

    protected void prepareRefresh() {
        applicationEvents = new LinkedHashSet<>();
        this.startupDate = System.currentTimeMillis();
    }

    protected ListableBeanFactory obtainBeanFactory() {
        refreshBeanFactory();
        ListableBeanFactory beanFactory = getBeanFactory();
        System.out.println(beanFactory);
        return beanFactory;
    }

    protected void prepareBeanFactory(ListableBeanFactory beanFactory) {

    }

    public abstract ListableBeanFactory getBeanFactory();

    public DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    //---------------------------------------------------------------------
    // Implementation of BeanFactory interface
    //---------------------------------------------------------------------

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) {
        return getBeanFactory().getBean(requiredType, args);
    }

    @Override
    public boolean containsBean(String name) {
        return getBeanFactory().containsBean(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return getBeanFactory().isSingleton(name);
    }


    //---------------------------------------------------------------------
    // Implementation of ListableBeanFactory interface
    //---------------------------------------------------------------------

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return getBeanFactory().getBeanNamesForType(type);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }


    //---------------------------------------------------------------------
    // Implementation of Lifecycle interface
    //---------------------------------------------------------------------

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }


    //---------------------------------------------------------------------
    // Abstract methods
    //---------------------------------------------------------------------

    /**
     * 刷新beanFactory，由子类实现
     */
    protected abstract void refreshBeanFactory();

}
