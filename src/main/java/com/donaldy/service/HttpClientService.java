package com.donaldy.service;

import com.donaldy.model.User;
import com.donaldy.utils.HttpRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
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

    public void test3() {

        HttpEntity<String> response = restTemplate.exchange("http://object-service.test.thundersdata.com/file/download?fileId=6748&access_token=0fe45a97125ec49cffcd6ab7d462dcb5", HttpMethod.GET, null, String.class);

        String resultString = response.getBody();
        HttpHeaders headers = response.getHeaders();

        System.out.println(headers);
    }

    public void test4() {

        String _url = "http://object-service-fn.test.thundersdata.com/file/download?fileId=6748&access_token=af68c59aaf19b490481f77a878b6d82b";

        String resp = HttpRequestBuilder.get().url(_url).build().invoke();

        System.out.println(resp);
    }
}
