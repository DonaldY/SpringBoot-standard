package com.donaldy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author donald
 * @date 2022/08/27
 */
@RequestMapping(value = "/http")
@RestController
public class HttpController {

    @GetMapping("/test")
    public void ping(HttpServletResponse response) {

        response.setHeader("expire ", "-1");
        response.setHeader("Content-Length", "0");

    }

    @GetMapping("/test2")
    public Map<String, String> ping2(HttpServletResponse response) {
        response.setStatus(500);
        Map<String, String> map = new HashMap<>();
        map.put("code1", "111");
        //map.put("message", null);

        return map;
    }
}
