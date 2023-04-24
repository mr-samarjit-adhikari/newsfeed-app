package com.flipkart.test.FlipKartNewsFeed.configuration;

import org.jline.utils.AttributedString;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Configuration
public class ApplicationConfigurations implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        String prompt = "NewsFeedApp:>";
        return new AttributedString(prompt, 0,prompt.length());
    }
}
