package io.vinson.framework.context;


/**
 * @Description:
 *
 * @author: jiangweixin
 * @date: 2018/12/25
 */
public class FileSystemXmlApplicationContext extends AbstractXmlApplicationContext {


    public FileSystemXmlApplicationContext() {

    }

    public FileSystemXmlApplicationContext(ApplicationContext parent) {
        super(parent);
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

    }

}
