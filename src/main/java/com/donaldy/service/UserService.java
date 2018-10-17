package com.donaldy.service;

import com.donaldy.model.User;

import java.util.List;

/**
 * Created by DonaldY on 2018/7/11.
 */
public interface UserService {

    public void executeUserInfo(Integer userId);

    public List<User> getUserListByName(String username);

}
