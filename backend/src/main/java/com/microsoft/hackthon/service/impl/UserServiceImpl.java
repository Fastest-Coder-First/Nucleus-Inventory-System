package com.microsoft.hackthon.service.impl;

import com.microsoft.hackthon.dao.UserRepository;
import com.microsoft.hackthon.entity.User;
import com.microsoft.hackthon.exception.UserNotFound;
import com.microsoft.hackthon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        // find all users from database


        return userRepository.findAll();
    }

    @Override
    public User getUser(UUID id) {

        // find user with id from database with user repository

        return userRepository.findById(id).orElseThrow( () -> new UserNotFound("User not found") );

    }

    @Override
    public User addUser(User user) {
        // add user to database with user repository if user exist throw exception
        userRepository.findById(user.getUserId()).orElseThrow( () -> new UserNotFound("User not found") );
        user.setUserId(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        // add user to database with user repository  and check if user already exist
        User existingUser = userRepository.findById(user.getUserId()).orElseThrow( () -> new UserNotFound("User not found") );
        existingUser.setUserName(user.getUserName());
        existingUser.setUserPassword(user.getUserPassword());
        existingUser.setUserEmail(user.getUserEmail());
        existingUser.setUserPhone(user.getUserPhone());
        existingUser.setUserAddress(user.getUserAddress());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(UUID id) {
        // delete user from database with user repository and check if user already exist
        userRepository.findById(id).orElseThrow( () -> new UserNotFound("User not found") );
        userRepository.deleteById(id);
    }
}
