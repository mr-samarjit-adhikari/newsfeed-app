package com.flipkart.test.FlipKartNewsFeed.commands;

import com.flipkart.test.FlipKartNewsFeed.helper.ShellHelper;
import com.flipkart.test.FlipKartNewsFeed.model.entities.NewsFeed;
import com.flipkart.test.FlipKartNewsFeed.model.entities.User;
import com.flipkart.test.FlipKartNewsFeed.model.entities.UserComment;
import com.flipkart.test.FlipKartNewsFeed.model.entities.UserVote;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ShellComponent
@ShellCommandGroup("News-Feed Commands")
public class ApplicationCliOperations extends SecureCommand{
    private ShellHelper helper;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private NewsFeedService newsFeedService;
    private UserCommentService userCommentService;

    @Autowired
    public ApplicationCliOperations(@Lazy ShellHelper shellHelper,
                                    AuthenticationManager authManager, BCryptPasswordEncoder passwordEncoder,
                                    UserService userService,NewsFeedService feedService,
                                    UserCommentService userCommentService){
        this.helper = shellHelper;
        this.authenticationManager = authManager;
        this.userService = userService;
        this.newsFeedService = feedService;
        this.userCommentService = userCommentService;
        this.passwordEncoder = passwordEncoder;
    }

    @ShellMethod(value="A user can signup to the system")
    public void signup(@Size(min=1,max=255) String username, @Size(min = 1,max=16) String password){
        User signupUser = userService.register(username,passwordEncoder.encode(password));
        helper.print("Successfully register a user with username:"+username+" and Id:"+signupUser.getId());
    }

    @ShellMethod(value="A user will be able to login to the system")
    public void login(@Size(min=1,max=255) String username, @Size(min=1,max=16) String password){
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

    @ShellMethod(value="A logged-in user can post a feed item")
    @ShellMethodAvailability("isUserLoggedIn")
    public void post(String post_text){
        try{
            User currUser = findCurrLoggedInUser();
            NewsFeed newsFeed = new NewsFeed(post_text);
            newsFeed = newsFeedService.persist(newsFeed);
            newsFeed.setPostOwner(currUser);
            newsFeed.setUserVote(new UserVote());
            //save the data
            userService.persist(currUser);
            newsFeedService.persist(newsFeed);
            helper.print(String.format("User %s added a post with feed-Id:%d",currUser.getUserName(), newsFeed.getPostId()));
        }catch(Exception e){
            helper.print("Error occurred: "+e.getMessage());
        }
    }

    @ShellMethod(value="Current logged-in user can follow other user")
    @ShellMethodAvailability("isUserLoggedIn")
    public void follow(Integer followerId){
        try {
            //get the current user
            User currUser = findCurrLoggedInUser();
            User followee = userService.findById(Long.valueOf(followerId));
            currUser.setFollowee(followee);
            userService.persist(currUser);
            helper.print(currUser.getUserName()+" Started following user "+followee.getUserName());
        }catch(Exception e){
            helper.print("Error occurred. Reason: "+e.getMessage());
        }
    }

    @ShellMethod(value="A user can comment on another user's feed item")
    @ShellMethodAvailability("isUserLoggedIn")
    public void reply(Integer feedId,String reply_text){
        try{
            //get the current user
            User currUser = findCurrLoggedInUser();

            //Now find the feed
            NewsFeed newsFeed = newsFeedService.findById(Long.valueOf(feedId));
            UserComment newComment = new UserComment(reply_text, new Timestamp(System.currentTimeMillis()));
            newComment = userCommentService.persist(newComment);
            newComment.setOwner(currUser);
            newComment.setFeed(newsFeed);

            //now save the data
            newsFeedService.persist(newsFeed);
            userCommentService.persist(newComment);
            userService.persist(currUser);
            helper.print("Successfully replied to feed "+feedId);
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
            newsFeedService.persist(newsFeed);
            helper.print("Feed "+newsFeed.getPostId()+" got UpVote");
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
            helper.print("Feed "+newsFeed.getPostId()+" got DownVote");
        }catch(Exception e){
            helper.print("error occurred in downvote "+e.getMessage());
        }
    }

    @ShellMethod(value="Shows the news feed for a current logged-in user")
    @ShellMethodAvailability("isUserLoggedIn")
    public List<String> shownewsfeed(){
        //get the current user
        User currUser = findCurrLoggedInUser();
        List<Long> leaderIds = userService.findLeaders(currUser);
        List<NewsFeed>  newsFeeds = newsFeedService.findPostsByFollowedUsers(leaderIds);
        List<String> feedList = new ArrayList<>(5);
        newsFeeds.forEach((feed)->{
            feedList.add(feed.getText());
        });
        //find News feed by higher vote count
//        newsFeeds = newsFeedService.findPostsByHighScores();
//        newsFeeds.forEach((feed)->{
//            feedList.add(feed.getText());
//        });

        return feedList;
    }

    private User findCurrLoggedInUser() throws RuntimeException{
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User currUser = userService.findByName(userName);
        return currUser;
    }
}
