package com.donaldy.controller;

import com.donaldy.model.User;
import com.donaldy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DonaldY on 2018/7/11.
 */
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> query() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
    
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public List<User> queryUser(@RequestParam String username) {
        System.out.println("username : " + username);
        List<User> users = new ArrayList<>();
        User user = new User();
        users.add(user);
        return users;
    }
    
}
