package com.jacky.springframework.context.event;


import com.jacky.springframework.context.MyApplicationContext;
import com.jacky.springframework.context.MyApplicationEvent;

/**
 * Base class for events raised for an <code>ApplicationContext</code>.
 *
 *
 *
 *
 *
 *
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class MyApplicationContextEvent extends MyApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MyApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final MyApplicationContext getApplicationContext() {
        return (MyApplicationContext) getSource();
    }

}
