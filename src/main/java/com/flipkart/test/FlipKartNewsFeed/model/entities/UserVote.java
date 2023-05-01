package com.flipkart.test.FlipKartNewsFeed.model.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserVote {
    private Integer upVoteCount;
    private Integer downVoteCount;
    private Integer finalScore;

    public UserVote(){
        this(0,0);
    }
    public UserVote(Integer upVoteCount,Integer downVoteCount){
        this.upVoteCount = upVoteCount;
        this.downVoteCount = downVoteCount;
        this.finalScore = 0;
    }
    public void upVote(){
        this.upVoteCount ++;
        this.finalScore = this.upVoteCount-this.downVoteCount;
    }
    public void downVote(){
        this.downVoteCount++;
        this.finalScore = this.upVoteCount-this.downVoteCount;
        if (finalScore<0){
            this.finalScore = 0;
        }
    }
    public Integer getScore(){
        return finalScore;
    }
}
