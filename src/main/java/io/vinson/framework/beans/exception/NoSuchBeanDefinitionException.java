package io.vinson.framework.beans.exception;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2019/1/3
 */
public class NoSuchBeanDefinitionException extends RuntimeException {

    public NoSuchBeanDefinitionException(String msg) {
        super(msg);
    }
}
