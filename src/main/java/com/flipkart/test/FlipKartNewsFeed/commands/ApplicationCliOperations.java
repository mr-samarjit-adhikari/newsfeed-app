package com.flipkart.test.FlipKartNewsFeed.commands;

import com.flipkart.test.FlipKartNewsFeed.helper.ShellHelper;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@ShellCommandGroup("News-Feed Commands")
public class ApplicationCliOperations {
    private ShellHelper helper;
    private AuthenticationManager authenticationManager;

    public ApplicationCliOperations(@Lazy@Autowired ShellHelper shellHelper,
                                    @Autowired AuthenticationManager authManager){
        this.helper = shellHelper;
        this.authenticationManager = authManager;
    }

    @ShellMethod(value="A user can signup to the system")
    public void signup(@Size(min=1,max=255) String username, @Size(min = 1,max=16) String password){

    }

    @ShellMethod(value="A user will be able to login to the system")
    public void login(@Size(min=1,max=255) String username, @Size(min=8,max=16) String password){
        Authentication request = new UsernamePasswordAuthenticationToken(username,password);
        try{
            Authentication authResult = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            helper.print("Successfully Authenticated for user: "+username);
        }catch(AuthenticationException e){
            helper.print("Authentication Failed! Reason: "+e.getMessage());
        }
    }

    @ShellMethod(value="A user can post a feed item")
    @ShellMethodAvailability("isUserLoggedIn")
    public void post(Integer userId,String text){

    }

    @ShellMethod(value="Users can follow other users")
    @ShellMethodAvailability("isUserLoggedIn")
    public void follow(Integer userId,Integer followers){

    }

    @ShellMethod(value="A user can comment on another user's feed item")
    @ShellMethodAvailability("isUserLoggedIn")
    public void reply(Integer feedId,String reply_text){

    }

    @ShellMethod(value="Upvote the post")
    @ShellMethodAvailability("isUserLoggedIn")
    public void upvote(Integer userId,Integer feedId){

    }

    @ShellMethod(value="Downvote the post")
    @ShellMethodAvailability("isUserLoggedIn")
    public void downvote(Integer userId,Integer feedId){

    }

    @ShellMethod(value="Any user can read his news feed")
    @ShellMethodAvailability("isUserLoggedIn")
    public void shownewsfeed(Integer userId){

    }
}
