package com.donaldy.controller;

import com.donaldy.common.Const;
import com.donaldy.common.ServerResponse;
import com.donaldy.handler.RestfulException;
import com.donaldy.model.User;
import com.donaldy.model.User2;
import com.donaldy.service.UserService;
import com.donaldy.utils.Page;
import com.donaldy.utils.constraints.annotations.IsMobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Arrays;
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

    @GetMapping("/list")
    public ServerResponse getUserList(Integer page, Integer pageSize) {

        System.out.println(page + "  " + pageSize);

        List<User2> users = Arrays.asList(
                User2.newBuilder().id(1).firstName("AngularJS-1").lastName("Angular-1").username("angular-1").build(),
                User2.newBuilder().id(2).firstName("AngularJS-2").lastName("Angular-2").username("angular-2").build()
        );

        // throw new RestfulException(Const.HttpStatusCode.UNAUTHORIZED.getCode(), "error");

        return ServerResponse.createBySuccess(new Page<User2>(10, users));
    }
    
}