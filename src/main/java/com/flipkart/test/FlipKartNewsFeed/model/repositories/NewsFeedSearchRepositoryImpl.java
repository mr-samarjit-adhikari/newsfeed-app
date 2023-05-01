package com.flipkart.test.FlipKartNewsFeed.model.repositories;

import com.flipkart.test.FlipKartNewsFeed.model.entities.NewsFeed;
import com.flipkart.test.FlipKartNewsFeed.model.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsFeedSearchRepositoryImpl implements NewsFeedSearchRepository{
    private EntityManager entityManager;

    @Autowired
    public NewsFeedSearchRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public List<NewsFeed> findPostsByFollowedUsers(List<Long> userIds) {
        CriteriaBuilder criteriaBuilder =  entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsFeed> criteriaQuery = criteriaBuilder.createQuery(NewsFeed.class); //typeof a row in result

        Root<NewsFeed> root = criteriaQuery.from(NewsFeed.class);
        Join<NewsFeed, User> author = root.join("postOwner");
        Predicate inPredicate =  author.get("userId").in(userIds);
        criteriaQuery.where(inPredicate);

        List<NewsFeed> newsFeeds =  entityManager.createQuery(criteriaQuery).getResultList();
        return newsFeeds;
    }

    @Override
    public List<NewsFeed> findFirst10PostsByHighScores() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsFeed> criteriaQuery = criteriaBuilder.createQuery(NewsFeed.class);

        Root<NewsFeed> root = criteriaQuery.from(NewsFeed.class);
        Path<Integer> finalScorePath = root.get("userVote").get("finalScore");
        criteriaQuery.orderBy(criteriaBuilder.desc(finalScorePath));

        List<NewsFeed> newsFeeds = entityManager.createQuery(criteriaQuery).setMaxResults(10).getResultList();
        return newsFeeds;
    }

    @Override
    public List<NewsFeed> findPostsByHighComments() {
        return null;
    }

    @Override
    public List<NewsFeed> findPostsByRecentTimestamp() {
        return null;
    }
}
