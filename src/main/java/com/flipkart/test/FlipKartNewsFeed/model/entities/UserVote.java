package com.flipkart.test.FlipKartNewsFeed.model.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserVote {
    private Integer upVoteCount;
    private Integer downVoCount;

    public UserVote(){
        this(0,0);
    }
    public UserVote(Integer upVoteCount,Integer downVoCount){
        this.upVoteCount = upVoteCount;
        this.downVoCount = downVoCount;
    }
    public void upVote(){
        this.upVoteCount ++;
    }
    public void downVote(){
        this.downVoCount ++;
    }
    public Integer getScore(){
        return upVoteCount-downVoCount;
    }
}
