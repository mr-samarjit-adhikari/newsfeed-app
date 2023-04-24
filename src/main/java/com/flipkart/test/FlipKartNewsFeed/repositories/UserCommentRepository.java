package com.flipkart.test.FlipKartNewsFeed.repositories;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommentRepository extends JpaRepository<UserComment,Long> {
}
