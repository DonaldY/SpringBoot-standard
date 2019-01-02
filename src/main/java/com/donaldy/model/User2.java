package com.donaldy.model;

import lombok.Data;

@Data
public class User2 {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;

    private User2(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        username = builder.username;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String firstName;
        private String lastName;
        private String username;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public User2 build() {
            return new User2(this);
        }
    }
}
