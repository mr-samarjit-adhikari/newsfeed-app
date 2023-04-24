package com.flipkart.test.FlipKartNewsFeed.repositories;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.UserVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVoteRepository extends JpaRepository<UserVote,Long> {
}
