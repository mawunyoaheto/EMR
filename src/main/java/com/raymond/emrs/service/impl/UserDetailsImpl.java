package com.raymond.emrs.service.impl;

import com.raymond.emrs.entity.MyUserDetails;
import com.raymond.emrs.entity.User;
import com.raymond.emrs.exceptions.ResourceNotFoundException;
import com.raymond.emrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.of(userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: "+username, HttpStatus.NOT_FOUND)));
        return new MyUserDetails(user.get());
    }


}
