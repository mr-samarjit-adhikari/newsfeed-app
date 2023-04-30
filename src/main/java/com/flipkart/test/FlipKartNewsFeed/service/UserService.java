package com.flipkart.test.FlipKartNewsFeed.service;

import com.flipkart.test.FlipKartNewsFeed.model.entities.User;

import java.util.List;

public interface UserService {
    User register(String username,String password);
    User findByName(String username);
    User findById(Long userid);
    User persist(User user);
    List<Long> findLeaders(User currUser);
}
