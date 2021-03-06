package io.vinson.framework.beans.factory.support;

import io.vinson.framework.beans.factory.config.BeanDefinition;
import io.vinson.framework.beans.factory.config.BeanDefinitionHolder;
import org.w3c.dom.Element;


/**
 * @Description:
 * @author: jiangweixin
 * @date: 2019/1/2
 */
public class XmlBeanDefinitionParser {

    public static final String TRUE_VALUE = "true";

    public static final String FALSE_VALUE= "false";

    public static final String BEAN_ELEMENT = "bean";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String ABSTRACT_ATTRIBUTE = "abstract";

    public static final String SINGLETON_ATTRIBUTE = "singleton";

    public static final String INIT_METHOD_ATTRIBUTE = "init-method";

    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

    public static final String NULL_ELEMENT = "null";


    public BeanDefinitionHolder parseBeanDefinitionElement(Element element, BeanDefinition beanDefinition) {
        String id = element.getAttribute(ID_ATTRIBUTE);
        String name = element.getAttribute(NAME_ATTRIBUTE);

        // TODO

        return new BeanDefinitionHolder(beanDefinition, id);
    }
}
