package com.flipkart.test.FlipKartNewsFeed.repositories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="votes")
public class UserVote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long voteId;
    private Integer upVoteCount;
    private Integer downVoCount;

    public long getVoteId() {
        return voteId;
    }
    public Integer getScore(){
        return upVoteCount-downVoCount;
    }
}
