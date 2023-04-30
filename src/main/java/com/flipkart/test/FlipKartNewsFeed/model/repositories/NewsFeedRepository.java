package com.flipkart.test.FlipKartNewsFeed.model.repositories;

import com.flipkart.test.FlipKartNewsFeed.model.entities.NewsFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsFeedRepository extends JpaRepository<NewsFeed,Long>, NewsFeedSearchRepository {
}
