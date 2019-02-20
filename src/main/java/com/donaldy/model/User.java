package com.donaldy.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by DonaldY on 2018/7/13.
 */
@Data
public class User {
    
    @NotBlank(message = "用户类中用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    public User() {}

    private User(Builder builder) {
        setUsername(builder.username);
        setPassword(builder.password);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String username;
        private String password;

        private Builder() {
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
