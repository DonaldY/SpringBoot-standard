package com.donaldy.config.event;

import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @author donald
 * @date 2022/01/28
 */
@ToString
public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) { super(source); }
}