package com.donaldy.service;

import com.donaldy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpClientService {

    @Autowired
    private RestTemplate restTemplate;

    public String test() {

        // 模拟发送 GET 类型的 HTTP 请求
        return restTemplate.getForObject("/ping", String.class);
    }

    public User test2() {

        return restTemplate.getForObject("/ping2", User.class);
    }
}
