package com.raymond.emrs.service;

import com.raymond.emrs.entity.User;

import java.util.List;

public interface UserService {
    public User getUser(long userId);
    public List<User> getAllUsers();
    public User addUser(User user);
    public void deleteUser(long userId);
}
