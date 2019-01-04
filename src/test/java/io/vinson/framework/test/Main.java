package io.vinson.framework.test;

import io.vinson.framework.beans.factory.BeanFactory;
import io.vinson.framework.context.FileSystemXmlApplicationContext;

/**
 * @Description:
 * @author: jiangweixin
 * @date: 2019/1/4
 */
public class Main {

    public static void main(String[] args) {
//        BeanFactory beanFactory = new FileSystemXmlApplicationContext(new String[] {"framework.xml"}, true);
        BeanFactory beanFactory = new FileSystemXmlApplicationContext("framework.xml");
        beanFactory.getBean("testService", TestService.class).sayHello();
//        testService.sayHello();

    }
}
