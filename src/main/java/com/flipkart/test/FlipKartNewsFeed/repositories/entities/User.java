package com.flipkart.test.FlipKartNewsFeed.repositories.entities;

import com.flipkart.test.FlipKartNewsFeed.repositories.entities.NewsPost;
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
    @OneToMany(mappedBy = "post_owner")
    private Set<NewsPost> newsPosts;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="leader_id")
    private User leader;
    @OneToMany(mappedBy = "leader")
    private Set<User> followers= new HashSet<>();

    public User(String username,String password){
        this.username = username;
        this.password = password;
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

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }
}
