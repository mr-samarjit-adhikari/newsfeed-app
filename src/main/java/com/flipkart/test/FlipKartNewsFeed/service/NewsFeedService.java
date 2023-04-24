package com.flipkart.test.FlipKartNewsFeed.service;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.NewsFeed;

public interface NewsFeedService {
    NewsFeed findById(Long Id);
    NewsFeed persist(NewsFeed newsFeed);
}
