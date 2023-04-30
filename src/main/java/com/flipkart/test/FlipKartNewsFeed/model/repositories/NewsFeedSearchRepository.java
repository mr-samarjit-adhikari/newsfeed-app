package com.flipkart.test.FlipKartNewsFeed.model.repositories;

import com.flipkart.test.FlipKartNewsFeed.model.entities.NewsFeed;

import java.util.List;

public interface NewsFeedSearchRepository {
    List<NewsFeed> findPostsByFollowedUsers(List<Long> userIds);
    List<NewsFeed> findPostsByHighScores();
    List<NewsFeed> findPostsByHighComments();
    List<NewsFeed> findPostsByRecentTimestamp();
}
