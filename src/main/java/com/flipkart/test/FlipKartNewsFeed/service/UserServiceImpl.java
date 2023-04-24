package com.flipkart.test.FlipKartNewsFeed.service;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.User;
import com.flipkart.test.FlipKartNewsFeed.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.repository = userRepository;
    }
    @Override
    public void register(String username, String password) {
        User user = new User(username,password);
        this.repository.save(user);
    }
}
