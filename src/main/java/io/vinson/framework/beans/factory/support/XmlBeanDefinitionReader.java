package io.vinson.framework.beans.factory.support;

import io.vinson.framework.core.io.Resource;
import io.vinson.framework.core.util.Assert;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * 解析xml的bean
 * @author: jiangweixin
 * @date: 2018/12/29
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {


    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public int loadBeanDefinitions(Resource resource) {
        Assert.notNull(resource, "Resource must not be null");

        InputStream inputStream = null;
        try {
            try {
                inputStream = resource.getInputStream();
                doLoadBeanDefinitions(resource);
            } finally {
                inputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        doLoadBeanDefinitions(resource);
        return 0;
    }

    protected int doLoadBeanDefinitions(Resource resource) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(resource.getInputStream());
            int count = registerBeanDefinitions(document, resource);
            return count;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int registerBeanDefinitions(Document document, Resource resource) {

        int beforeCount = getRegistry().getBeanDefinitionCount();

        return getRegistry().getBeanDefinitionCount() - beforeCount;
    }
}
