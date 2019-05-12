package com.donaldy.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
class ExceptionEvent extends ApplicationEvent {

    private String message;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    ExceptionEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}
