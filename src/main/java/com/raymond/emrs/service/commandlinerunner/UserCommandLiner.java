package com.raymond.emrs.service.commandlinerunner;

import com.raymond.emrs.entity.User;
import com.raymond.emrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLiner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new User("ray","test","rmkaheto@gmail.com"));

    }
}
