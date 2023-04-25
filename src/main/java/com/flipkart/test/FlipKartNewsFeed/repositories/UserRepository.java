package com.flipkart.test.FlipKartNewsFeed.repositories;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);
}
