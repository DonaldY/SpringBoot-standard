package com.donaldy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/i18n")
public class I18nController {

    @Autowired
    MessageSource messageSource;

    @GetMapping(value = "/test1")
    public String test1() {

        // 在header中设置， Accept-Language: zh-CN， 查看效果
        //                Accept-Language: en-US
        //                Accept-Language: fr-FR
        return messageSource.getMessage("test_message", null, LocaleContextHolder.getLocale());
    }

}
