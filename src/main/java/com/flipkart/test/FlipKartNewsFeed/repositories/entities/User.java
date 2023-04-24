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

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String username;
    private String password;
    @OneToMany(mappedBy = "post_owner",cascade={CascadeType.ALL})
    private Set<NewsFeed> newsFeeds;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="leader_id")
    private User leader;
    @OneToMany(mappedBy = "leader")
    private Set<User> followers= new HashSet<>();

    @OneToMany(mappedBy = "owner")
    private Set<UserComment> comments;

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }

    public Set<NewsFeed> getNewsFeeds() {
        return newsFeeds;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return userId;
    }

    public User getLeader() {
        return leader;
    }

    public Set<User> getFollowers() {
        return followers;
    }
}
