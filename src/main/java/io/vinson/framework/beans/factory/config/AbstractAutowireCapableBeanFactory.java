package io.vinson.framework.beans.factory.config;

import io.vinson.framework.beans.factory.BeanFactory;
import io.vinson.framework.beans.factory.support.AbstractBeanFactory;
import io.vinson.framework.beans.factory.support.RootBeanDefinition;

/**
 * @Description:
 *
 * @author: jiangweixin
 * @date: 2018/12/21
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {


    public AbstractAutowireCapableBeanFactory() {
        super();
    }

    public AbstractAutowireCapableBeanFactory(BeanFactory parentBeanFactory) {
        this();
        setParentBeanFactory(parentBeanFactory);
    }

    public <T> T createBean(Class<T> beanClass) {
        RootBeanDefinition rootBean = new RootBeanDefinition(beanClass);
        return (T) createBean(beanClass.getName(), rootBean, null);
    }

    protected Object createBean(String beanName, RootBeanDefinition rootBean, Object[] args) {

        RootBeanDefinition mbdToUse = rootBean;
        try {
            Class<?> resolvedClass = resolveBeanClass(beanName, rootBean);
            if(resolvedClass != null && !rootBean.hasBeanClass() && rootBean.getBeanClass() != null) {
                mbdToUse = new RootBeanDefinition(rootBean);
                mbdToUse.setBeanClass(resolvedClass);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object beanInstance = null;
        try {
            beanInstance = doCreateBean(beanName, mbdToUse, args);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return beanInstance;
    }

    protected Object doCreateBean(String beanName, RootBeanDefinition rootBean, Object[] args) throws ClassNotFoundException {
        Class<?> beanClass = resolveBeanClass(beanName, rootBean);

        return beanClass;
    }


//    protected Object createBeanInstance(String beanName, RootBeanDefinition rootBean, Object[] args) {
//        Class<?> beanClass = resolveBeanClass(beanName, rootBean);
//        return beanClass;
//    }
}
