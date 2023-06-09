package com.flipkart.test.FlipKartNewsFeed.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String userName;
    private String password;
    //for hibernate
    public User(){}
    @OneToMany(mappedBy = "postOwner",cascade={CascadeType.ALL})
    private Set<NewsFeed> newsFeeds;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="followee_id")
    private User followee;
    @OneToMany(mappedBy = "followee")
    private Set<User> followers= new HashSet<>();

    @OneToMany(mappedBy = "owner")
    private Set<UserComment> comments;

    public User(String username,String password){
        this.userName = username;
        this.password = password;
    }

    public Set<NewsFeed> getNewsFeeds() {
        return newsFeeds;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return userId;
    }

    public User getFollowee() {
        return followee;
    }

    public void setFollowee(User followee) {
        this.followee = followee;
    }

    public Set<User> getFollowers() {
        return followers;
    }
}
