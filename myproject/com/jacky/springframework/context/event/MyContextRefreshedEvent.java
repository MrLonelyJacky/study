package com.jacky.springframework.context.event;

/**
 * Event raised when an <code>ApplicationContext</code> gets initialized or refreshed.
 *
 *
 *
 *
 *
 *
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class MyContextRefreshedEvent extends MyApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MyContextRefreshedEvent(Object source) {
        super(source);
    }

}
