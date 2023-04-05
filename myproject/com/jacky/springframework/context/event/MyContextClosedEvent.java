package com.jacky.springframework.context.event;

/**
 * Event raised when an <code>ApplicationContext</code> gets closed.
 *
 *
 *
 *
 *
 *
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class MyContextClosedEvent extends MyApplicationContextEvent{

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MyContextClosedEvent(Object source) {
        super(source);
    }

}
