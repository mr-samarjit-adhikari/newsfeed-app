package com.flipkart.test.FlipKartNewsFeed.service;

import com.flipkart.test.FlipKartNewsFeed.model.entities.User;
import com.flipkart.test.FlipKartNewsFeed.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.repository = userRepository;
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

    @Override
    public List<Long> findLeaders(User currUser) {
        return repository.findFirst10Leaders(currUser.getId());
    }
}
