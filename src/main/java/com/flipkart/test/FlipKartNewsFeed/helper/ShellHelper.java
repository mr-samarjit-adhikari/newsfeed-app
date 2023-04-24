package com.flipkart.test.FlipKartNewsFeed.helper;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShellHelper {
    private Terminal terminal;

    public ShellHelper(@Autowired Terminal terminal) {
        this.terminal = terminal;
    }
    public void print(String message) {
        terminal.writer().println(message);;
    }
}
