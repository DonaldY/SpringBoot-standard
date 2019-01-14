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
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<User2> users = Arrays.asList(
            User2.newBuilder().id(1).firstName("AngularJS-1").lastName("Angular-1").username("angular-1").build(),
            User2.newBuilder().id(2).firstName("AngularJS-2").lastName("Angular-2").username("angular-2").build(),
            User2.newBuilder().id(3).firstName("AngularJS-3").lastName("Angular-3").username("angular-3").build(),
            User2.newBuilder().id(4).firstName("AngularJS-4").lastName("Angular-4").username("angular-4").build(),
            User2.newBuilder().id(5).firstName("AngularJS-5").lastName("Angular-5").username("angular-5").build(),
            User2.newBuilder().id(6).firstName("AngularJS-6").lastName("Angular-6").username("angular-6").build(),
            User2.newBuilder().id(7).firstName("AngularJS-7").lastName("Angular-7").username("angular-7").build(),
            User2.newBuilder().id(8).firstName("AngularJS-8").lastName("Angular-8").username("angular-8").build(),
            User2.newBuilder().id(9).firstName("AngularJS-9").lastName("Angular-9").username("angular-9").build(),
            User2.newBuilder().id(10).firstName("AngularJS-10").lastName("Angular-10").username("angular-10").build()
    );

    @GetMapping("/list")
    public ServerResponse getUserList(Integer page, Integer pageSize) throws InterruptedException {

        System.out.println(page + "  " + pageSize);

        int offset = page * pageSize - 1;

        List<User2> user2s = users.stream().filter(t -> t.getId() >= offset && t.getId() < (offset + pageSize))
                .collect(Collectors.toList());

        // throw new RestfulException(Const.HttpStatusCode.UNAUTHORIZED.getCode(), "error");

        return ServerResponse.createBySuccess(new Page<User2>(users.size(), user2s));
    }

    @GetMapping("/delete")
    public ServerResponse deleteUser(@NotNull Integer msgId) {

        User2 user2 = users.stream().filter(t -> t.getId().equals(msgId)).findFirst().orElse(null);

        if (ObjectUtils.isEmpty(user2)) {

            return ServerResponse.createByErrorMessage("找不到这个用户");
        }

        users.remove(user2.getId());

        return ServerResponse.createBySuccessMessage("删除成功");
    }
    
}