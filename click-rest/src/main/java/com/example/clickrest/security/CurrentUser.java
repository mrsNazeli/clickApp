package com.example.clickrest.security;

import am.itspace.clickcommon.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Optional;

public class CurrentUser extends  User {
    private User user;

    public CurrentUser(Optional<User> user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user = user;
    }
    public User getUser() {
        return user;
    }

}
