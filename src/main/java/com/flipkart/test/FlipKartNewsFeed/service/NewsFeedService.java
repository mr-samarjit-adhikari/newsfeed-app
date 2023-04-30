package com.flipkart.test.FlipKartNewsFeed.service;

import com.flipkart.test.FlipKartNewsFeed.model.entities.NewsFeed;

import java.util.List;

public interface NewsFeedService {
    NewsFeed findById(Long Id);
    NewsFeed persist(NewsFeed newsFeed);
    List<NewsFeed> findPostsByFollowedUsers(List<Long> userIds);
}
