package com.donaldy.controller;

import com.donaldy.common.ServerResponse;
import com.donaldy.model.User;
import com.donaldy.service.UserService;
import com.donaldy.utils.constraints.annotations.IsMobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by DonaldY on 2018/7/11.
 */

@Validated
@RequestMapping(value = "/user")
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public User queryUser(@RequestParam String username) {

        return this.userService.getUser(username, new User());
    }
    
    @GetMapping(value = "/{id:\\d+}")
    public ServerResponse getInfo(@PathVariable(value = "id") String id) {
        User user = new User();
        user.setUsername("Donald");
        return ServerResponse.createBySuccess(user);
    }

    @PostMapping
    public User getUser(@Valid User user, BindingResult errors) {
        
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(
                error -> System.out.println(error.getDefaultMessage())
            );
        }

        user.setUsername("Donald");
        return user;
    }

    @GetMapping("/friend")
    public User getUserFriend(@RequestParam String username) {

        return this.userService.getUserFriend(username);
    }

    @GetMapping("/phone")
    public String getUserPhone(@IsMobile String phone) {

        return phone;
    }
    
}
