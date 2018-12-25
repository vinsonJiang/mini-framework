package io.vinson.framework.context;

/**
 * @Description: 生命周期控制
 *
 * @author: jiangweixin
 * @date: 2018/12/15
 */
public interface Lifecycle {
    /**
     * 启动
     */
    void start();

    /**
     * 停止
     */
    void stop();

    /**
     * 是否运行中
     */
    boolean isRunning();
}
