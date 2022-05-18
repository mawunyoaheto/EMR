package com.raymond.emrs.service.impl;

import com.raymond.emrs.entity.User;
import com.raymond.emrs.exceptions.ResourceNotFoundException;
import com.raymond.emrs.repository.UserRepository;
import com.raymond.emrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User Not Found: " + userId, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Failed, user not found: " + userId, HttpStatus.NOT_FOUND));
        userRepository.delete(foundUser);
    }
}
