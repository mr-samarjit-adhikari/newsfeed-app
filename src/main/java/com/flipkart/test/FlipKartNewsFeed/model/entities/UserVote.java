package com.flipkart.test.FlipKartNewsFeed.model.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserVote {
    private Integer upVoteCount;
    private Integer downVoteCount;

    public UserVote(){
        this(0,0);
    }
    public UserVote(Integer upVoteCount,Integer downVoteCount){
        this.upVoteCount = upVoteCount;
        this.downVoteCount = downVoteCount;
    }
    public void upVote(){
        this.upVoteCount ++;
    }
    public void downVote(){
        this.downVoteCount++;
    }
    public Integer getScore(){
        return upVoteCount- downVoteCount;
    }
}
