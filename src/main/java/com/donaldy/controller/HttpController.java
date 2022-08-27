package com.donaldy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
}
