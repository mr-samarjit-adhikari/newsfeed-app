package com.flipkart.test.FlipKartNewsFeed.repositories.entities;

import jakarta.persistence.CascadeType;
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
    private UserVote userVote;
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="first_comment_id")
    private UserComment firstComment;
    @OneToMany(mappedBy = "firstComment")
    private Set<UserComment> replies;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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

    public void setReplies(Set<UserComment> replies) {
        this.replies = replies;
    }
}
