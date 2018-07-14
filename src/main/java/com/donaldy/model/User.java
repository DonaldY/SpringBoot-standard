package com.donaldy.model;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created by DonaldY on 2018/7/13.
 */
public class User {
    
    @NotBlank
    private String username;
    private String password;
    private Date birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
