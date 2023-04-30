package com.flipkart.test.FlipKartNewsFeed.model.repositories;

import com.flipkart.test.FlipKartNewsFeed.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);
    @Query("select u.userId from User u join u.followers f where f.userId=:userId order by u.userId limit 10")
    List<Long> findFirst10Leaders(@Param("userId")Long currentUserId);
}
