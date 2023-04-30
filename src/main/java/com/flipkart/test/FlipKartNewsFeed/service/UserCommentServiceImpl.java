package com.flipkart.test.FlipKartNewsFeed.service;

import com.flipkart.test.FlipKartNewsFeed.model.repositories.UserCommentRepository;
import com.flipkart.test.FlipKartNewsFeed.model.entities.UserComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommentServiceImpl implements UserCommentService{
    private UserCommentRepository commentRepository;
    @Autowired
    public UserCommentServiceImpl(UserCommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public UserComment persist(UserComment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public UserComment findById(Long commentId) {
        return null;
    }
}
