package com.flipkart.test.FlipKartNewsFeed.commands;

import com.flipkart.test.FlipKartNewsFeed.helper.ShellHelper;
import com.flipkart.test.FlipKartNewsFeed.repositories.entities.NewsFeed;
import com.flipkart.test.FlipKartNewsFeed.repositories.entities.User;
import com.flipkart.test.FlipKartNewsFeed.repositories.entities.UserComment;
import com.flipkart.test.FlipKartNewsFeed.service.NewsFeedService;
import com.flipkart.test.FlipKartNewsFeed.service.UserCommentService;
import com.flipkart.test.FlipKartNewsFeed.service.UserService;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.sql.Timestamp;

@ShellComponent
@ShellCommandGroup("News-Feed Commands")
public class ApplicationCliOperations {
    private ShellHelper helper;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private NewsFeedService newsFeedService;
    private UserCommentService userCommentService;

    public ApplicationCliOperations(@Lazy@Autowired ShellHelper shellHelper,
                                    @Autowired AuthenticationManager authManager,
                                    @Autowired UserService userService,
                                    @Autowired NewsFeedService feedService,
                                    @Autowired UserCommentService userCommentService){
        this.helper = shellHelper;
        this.authenticationManager = authManager;
        this.userService = userService;
        this.newsFeedService = feedService;
        this.userCommentService = userCommentService;
    }

    @ShellMethod(value="A user can signup to the system")
    public void signup(@Size(min=1,max=255) String username, @Size(min = 1,max=16) String password){
        userService.register(username,password);
        helper.print("Successfully register a user with username:"+username);
    }

    @ShellMethod(value="A user will be able to login to the system")
    public void login(@Size(min=1,max=255) String username, @Size(min=8,max=16) String password){
        Authentication request = new UsernamePasswordAuthenticationToken(username,password);
        try{
            Authentication authResult = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            User user = userService.findByName(username);
            helper.print("Successfully Authenticated for user: "+username+" with Id:"+user.getId());
        }catch(AuthenticationException e){
            helper.print("Authentication Failed! Reason: "+e.getMessage());
        }
    }

    @ShellMethod(value="A user can post a feed item")
    @ShellMethodAvailability("isUserLoggedIn")
    public void post(Integer userId,String text){
        try{
            User user = userService.findById(Long.valueOf(userId));
            NewsFeed newsFeed = new NewsFeed(text);
            user.getNewsFeeds().add(newsFeed);

            userService.persist(user);
            helper.print(String.format("User %s added a post with feed-Id:%d",user.getUsername(), newsFeed.getPostId()));
        }catch(UsernameNotFoundException e){
            helper.print("User not found: "+e.getMessage());
        }
    }

    @ShellMethod(value="Users can follow other users")
    @ShellMethodAvailability("isUserLoggedIn")
    public void follow(Integer userId,Integer followerId){
        try {
            User user = userService.findById(Long.valueOf(userId));
            User follower = userService.findById(Long.valueOf(followerId));

            user.getFollowers().add(follower);
            userService.persist(user);
        }catch(AuthenticationException e){
            helper.print("UserNot found!"+e.getMessage());
        }
    }

    @ShellMethod(value="A user can comment on another user's feed item")
    @ShellMethodAvailability("isUserLoggedIn")
    public void reply(Integer feedId,String reply_text){
        try{
            //get the current user
            Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User currUser = userService.findByName(userName);
            //Now find the feed
            NewsFeed newsFeed = newsFeedService.findById(Long.valueOf(feedId));
            UserComment newComment = new UserComment(reply_text, new Timestamp(System.currentTimeMillis()));
            newComment = userCommentService.persist(newComment);
            newComment.setOwner(currUser);
            newsFeed.getComments().add(newComment);
            //now save the data
            newsFeedService.persist(newsFeed);
            userCommentService.persist(newComment);
        }catch(Exception e){
            helper.print("error occurred "+e.getMessage());
        }
    }

    @ShellMethod(value="Upvote the post")
    @ShellMethodAvailability("isUserLoggedIn")
    public void upvote(Integer feedId){
        try {
            //Now find the feed
            NewsFeed newsFeed = newsFeedService.findById(Long.valueOf(feedId));
            newsFeed.getUserVote().upVote();
        }catch(Exception e){
            helper.print("error occurred in upvote "+e.getMessage());
        }
    }

    @ShellMethod(value="Downvote the post")
    @ShellMethodAvailability("isUserLoggedIn")
    public void downvote(Integer feedId){
        try {
            //Now find the feed
            NewsFeed newsFeed = newsFeedService.findById(Long.valueOf(feedId));
            newsFeed.getUserVote().downVote();
            newsFeedService.persist(newsFeed);
        }catch(Exception e){
            helper.print("error occurred in downvote "+e.getMessage());
        }
    }

    @ShellMethod(value="Any user can read his news feed")
    @ShellMethodAvailability("isUserLoggedIn")
    public void shownewsfeed(Integer userId){

    }
}
