package com.jacky.springframework.context;

import java.util.EventObject;

public abstract class MyApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MyApplicationEvent(Object source) {
        super(source);
    }
}
