package com.donaldy.controller;

import com.donaldy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by DonaldY on 2018/7/11.
 */
@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    
}
