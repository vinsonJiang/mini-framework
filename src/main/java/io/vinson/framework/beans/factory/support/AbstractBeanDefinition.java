package io.vinson.framework.beans.factory.support;

import io.vinson.framework.beans.factory.config.BeanDefinition;
import io.vinson.framework.core.io.Resource;
import io.vinson.framework.core.util.Assert;
import io.vinson.framework.core.util.ClassUtils;

/**
 * @Description:
 *
 * @author: jiangweixin
 * @date: 2018/12/26
 */
public abstract class AbstractBeanDefinition implements BeanDefinition, Cloneable {

    private volatile Object beanClass;

    private boolean isAbstract = false;

    private String description;

    private Resource resource;

    private String initMethodName;

    private String destroyMethodName;

    @Override
    public void setBeanClassName(String beanClassName) {
        this.beanClass = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        Object object = this.beanClass;
        if(object instanceof Class) {
            return ((Class<?>)object).getName();
        } else {
            return beanClass.toString();
        }
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        Object tempBeanClass = this.beanClass;
        Assert.notNull(tempBeanClass, "No beanClass on beanDefinition");
        if(!(tempBeanClass instanceof Class)) {
            throw new IllegalStateException("BeanClass name:[" + tempBeanClass + "] has not been resolved an class");
        }
        return (Class<?>)beanClass;
    }

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    /**
     * return 是否指定了beanClass
     * @return
     */
    public boolean hasBeanClass() {
        return this.beanClass instanceof Class;
    }

    public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException {
        String className = getBeanClassName();
        if(className == null) {
            return null;
        }
        Class<?> resolveClass = ClassUtils.forName(className, classLoader);
        this.beanClass = resolveClass;
        return resolveClass;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return cloneBeanDefinition();
    }

    /**
     * 复制beanDefinition的方法，由子类去实现
     * @return
     */
    public abstract AbstractBeanDefinition cloneBeanDefinition();

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
