package com.donaldy.service.impl;

import com.donaldy.service.ConditionService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(name = "condition.type", havingValue = "other")
@Service
public class OtherConditionServiceImpl implements ConditionService {

    @Override
    public void sendMessage() {
        System.out.println("other...");
    }
}
