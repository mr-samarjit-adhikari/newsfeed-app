package com.flipkart.test.FlipKartNewsFeed.repositories;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.NewsFeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsFeedRepository extends JpaRepository<NewsFeed,Long> {
}
