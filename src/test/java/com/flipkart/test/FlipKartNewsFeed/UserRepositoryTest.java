package com.flipkart.test.FlipKartNewsFeed;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.User;
import com.flipkart.test.FlipKartNewsFeed.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindingUserById_thenCorrect() {
        userRepository.save(new User("user", "password"));
        assertTrue(userRepository.findById(1L).isPresent());
    }
}
