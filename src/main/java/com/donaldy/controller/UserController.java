package com.donaldy.controller;

import com.donaldy.model.User;
import com.donaldy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DonaldY on 2018/7/11.
 */
@RequestMapping(value = "/user")
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public List<User> queryUser(@RequestParam String username) {

        return this.userService.getUserListByName(username);
    }
    
    @GetMapping(value = "/{id:\\d+}")
    public User getInfo(@PathVariable(value = "id") String id) {
        User user = new User();
        user.setUsername("Donald");
        return user;
    }
    
    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(
                error -> System.out.println(error.getDefaultMessage())
            );
        }
        
        System.out.println(user.getUsername());
        user.setUsername("Donald");
        return user;
    }
    
}
