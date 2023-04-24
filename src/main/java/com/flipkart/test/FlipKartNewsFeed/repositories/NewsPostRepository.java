package com.flipkart.test.FlipKartNewsFeed.repositories;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.NewsPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsPostRepository extends JpaRepository<NewsPost,Long> {
}
