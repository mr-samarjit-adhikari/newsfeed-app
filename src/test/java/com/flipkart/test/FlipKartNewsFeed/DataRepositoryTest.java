package com.flipkart.test.FlipKartNewsFeed;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.User;
import com.flipkart.test.FlipKartNewsFeed.repositories.DataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataRepositoryTest {
    @Autowired
    private DataRepository dataRepository;

    @Test
    public void whenFindingUserById_thenCorrect() {
        dataRepository.save(new User("user", "password"));
        assertTrue(dataRepository.findById(1L).isPresent());
    }
}
