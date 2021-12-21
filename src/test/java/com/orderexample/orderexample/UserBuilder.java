package com.orderexample.orderexample;

import com.orderexample.orderexample.models.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class UserBuilder {

    private UserBuilder() {
        throw new UnsupportedOperationException();
    }

    public static User build(String email){
        User user = new User();
        user.setEmail(email);
        return user;
    }

    public static Collection<User> buildBulkUser(){
        return Stream.of("aa@hotmail.com",
                        "bb@hotmail.com",
                        "cc@hotmail.com")
                .map(UserBuilder::build)
                .collect(Collectors.toList());
        }

    }

