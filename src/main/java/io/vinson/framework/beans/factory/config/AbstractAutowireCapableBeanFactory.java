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

    @Override
    public <T> T createBean(Class<T> beanClass) {
        RootBeanDefinition rootBean = new RootBeanDefinition(beanClass);
        return (T) createBean(beanClass.getName(), rootBean, null);
    }

    @Override
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



    /**
     * 获取 Bean 实例，执行{@code postProcessBeforeInitialization} 方法
     * 返回被修饰的bean
     * @param bean
     * @param beanName
     * @return
     */
    public Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) {
        Object result = bean;
        for(BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessorBeforeInitialization(result, beanName);
            if(current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    public Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) {
        Object result = bean;
        for(BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessorAfterInitialization(result, beanName);
            if(current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
