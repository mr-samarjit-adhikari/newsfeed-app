package com.flipkart.test.FlipKartNewsFeed.service;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.UserComment;

public interface UserCommentService {
    UserComment persist(UserComment comment);
    UserComment findById(Long commentId);
}
