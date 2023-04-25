package com.flipkart.test.FlipKartNewsFeed.service;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.User;

public interface UserService {
    User register(String username,String password);
    User findByName(String username);
    User findById(Long userid);
    User persist(User user);
}
