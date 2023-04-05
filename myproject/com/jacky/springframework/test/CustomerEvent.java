package com.jacky.springframework.test;

import com.jacky.springframework.context.event.MyApplicationContextEvent;
import lombok.Getter;

@Getter
public class CustomerEvent extends MyApplicationContextEvent {

    private Long id;
    private String message;


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomerEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }
}
