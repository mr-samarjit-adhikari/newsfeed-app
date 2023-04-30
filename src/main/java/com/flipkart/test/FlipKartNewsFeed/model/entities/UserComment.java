package com.flipkart.test.FlipKartNewsFeed.model.entities;

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

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="comments")
public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;
    private String text;
    private Timestamp timestamp;
    @Embedded
    private UserVote userVote;
    //comment owner
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="owner_id")
    private User owner;
    //comment on feed
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="feed_id")
    private NewsFeed feed;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="first_comment_id")
    private UserComment firstComment;
    @OneToMany(mappedBy = "firstComment")
    private Set<UserComment> replies;

    //for hibernate
    public UserComment(){}
    public UserComment(String comment_text,Timestamp ts){
        this.text = comment_text;
        this.timestamp = ts;
    }
    public long getCommentId() {
        return commentId;
    }

    public String getText() {
        return text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public UserVote getUserVote() {
        return userVote;
    }

    public void setUserVote(UserVote userVote) {
        this.userVote = userVote;
    }

    public UserComment getFirstComment() {
        return firstComment;
    }

    public void setFirstComment(UserComment firstComment) {
        this.firstComment = firstComment;
    }

    public Set<UserComment> getReplies() {
        return replies;
    }

    public User getOwner() {
        return owner;
    }

    public NewsFeed getFeed() {
        return feed;
    }

    public void setFeed(NewsFeed feed) {
        this.feed = feed;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
