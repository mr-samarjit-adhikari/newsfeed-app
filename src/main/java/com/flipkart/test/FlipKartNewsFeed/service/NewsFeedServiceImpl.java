package com.flipkart.test.FlipKartNewsFeed.service;

import com.flipkart.test.FlipKartNewsFeed.model.repositories.NewsFeedRepository;
import com.flipkart.test.FlipKartNewsFeed.model.entities.NewsFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsFeedServiceImpl implements NewsFeedService{
    private NewsFeedRepository feedRepository;

    @Autowired
    public NewsFeedServiceImpl(NewsFeedRepository feedRepository){
        this.feedRepository = feedRepository;
    }

    @Override
    public NewsFeed findById(Long feedId) {
        Optional<NewsFeed> newsFeed = feedRepository.findById(feedId);
        return newsFeed.get();
    }

    @Override
    public NewsFeed persist(NewsFeed newsFeed) {
        return feedRepository.saveAndFlush(newsFeed);
    }

    @Override
    public List<NewsFeed> findPostsByFollowedUsers(List<Long> userIds) {
        return feedRepository.findPostsByFollowedUsers(userIds);
    }

    @Override
    public List<NewsFeed> findPostsByHighScores() {
        return feedRepository.findPostsByHighScores();
    }

}
