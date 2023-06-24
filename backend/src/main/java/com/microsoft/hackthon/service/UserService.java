package com.microsoft.hackthon.service;

import com.microsoft.hackthon.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    //    create crud operation on user class
    public List<User> getUsers();
    public User getUser(UUID id);
    public User addUser(User user);
    public User updateUser(User user);
    public void deleteUser(UUID id);
}
