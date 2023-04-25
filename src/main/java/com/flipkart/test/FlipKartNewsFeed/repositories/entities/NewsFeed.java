package com.flipkart.test.FlipKartNewsFeed.repositories.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name="news_posts")
public class NewsFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;
    private String text;
    @Embedded
    private UserVote userVote;
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="user_id")
    private User post_owner;
    @OneToMany
    private Set<UserComment> comments;
    //for hibernate
    public NewsFeed(){}

    public NewsFeed(String newsPost){
        this.text = newsPost;
    }
    public long getPostId() {
        return postId;
    }

    public String getText() {
        return text;
    }

    public User getPost_owner() {
        return post_owner;
    }

    public void setPost_owner(User post_owner) {
        this.post_owner = post_owner;
    }

    public UserVote getUserVote() {
        return userVote;
    }

    public Set<UserComment> getComments() {
        return comments;
    }
}
