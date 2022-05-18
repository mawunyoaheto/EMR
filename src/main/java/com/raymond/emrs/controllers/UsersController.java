package com.raymond.emrs.controllers;

import com.raymond.emrs.entity.User;
import com.raymond.emrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UsersController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<User> getOneUser(@PathVariable(value = "userId") long userId){
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable(value = "userId") long userId){
        userService.deleteUser(userId);
    }
}
