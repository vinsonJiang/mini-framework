package io.vinson.framework.context;

import java.util.EventObject;

/**
 * @Description:
 *
 * @author: jiangweixin
 * @date: 2018/12/25
 */
public class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    private final long timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    public final long getTimestamp() {
        return timestamp;
    }
}
