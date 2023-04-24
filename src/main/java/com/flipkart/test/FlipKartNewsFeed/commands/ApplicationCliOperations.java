package com.flipkart.test.FlipKartNewsFeed.commands;

import jakarta.validation.constraints.Size;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("News-Feed Commands")
public class ApplicationCliOperations {

    @ShellMethod(value="A user can signup to the system")
    public void signup(@Size(min=1,max=255) String username, @Size(min = 1,max=16) String password){

    }

    @ShellMethod(value="A user will be able to login to the system")
    public void login(@Size(min=1,max=255) String username, @Size(min=8,max=16) String password){

    }

    @ShellMethod(value="A user can post a feed item")
    public void post(Integer userId,String text){

    }

    @ShellMethod(value="Users can follow other users")
    public void follow(Integer userId,Integer followers){

    }

    @ShellMethod(value="A user can comment on another user's feed item")
    public void reply(Integer feedId,String reply_text){

    }

    @ShellMethod(value="Upvote the post")
    public void upvote(Integer userId,Integer feedId){

    }

    @ShellMethod(value="Downvote the post")
    public void downvote(Integer userId,Integer feedId){

    }

    @ShellMethod(value="Any user can read his news feed")
    public void shownewsfeed(Integer userId){

    }
}
