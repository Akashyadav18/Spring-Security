package com.Demo.Security.Application.service;

import com.Demo.Security.Application.entity.UserEntity;
import com.Demo.Security.Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity getUserFromUserName(String usernameOrEmail) {
         return userRepo.findByUsernameAndIsActive(usernameOrEmail, true)
                 .or(() -> userRepo.findByEmailAndIsActive(usernameOrEmail, true))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UserEntity user = getUserFromUserName(usernameOrEmail);
        return User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        if(userRepo.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("UserName Already taken");
        }
        if(userRepo.findByUsername(user.getEmail()).isPresent()){
            throw new RuntimeException("Email Already taken");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setIsActive(true);

        return userRepo.save(userEntity);
    }
}
