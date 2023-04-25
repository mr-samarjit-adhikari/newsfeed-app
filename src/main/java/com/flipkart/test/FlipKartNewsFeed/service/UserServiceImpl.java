package com.flipkart.test.FlipKartNewsFeed.service;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.User;
import com.flipkart.test.FlipKartNewsFeed.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private DataRepository repository;

    @Autowired
    public UserServiceImpl(DataRepository dataRepository){
        this.repository = dataRepository;
    }
    @Override
    public User register(String username, String password) {
        User user = new User(username,password);
        return this.repository.save(user);
    }

    @Override
    public User findByName(String username) {
        User foundUser = repository.findByUserName(username);
        if (foundUser==null) throw new UsernameNotFoundException("Invalid username");
        return foundUser;
    }

    @Override
    public User findById(Long userid) {
        Optional<User> user = repository.findById(userid);
        if(user==null) throw new UsernameNotFoundException("User not found!");
        return user.get();
    }

    @Override
    public User persist(User user) {
        return repository.saveAndFlush(user);
    }
}
