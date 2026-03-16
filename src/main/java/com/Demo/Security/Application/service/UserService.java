package com.Demo.Security.Application.service;

import com.Demo.Security.Application.entity.UserEntity;

public interface UserService {

    UserEntity createUser(UserEntity user);

    UserEntity getUserFromUserName(String userName);

}

