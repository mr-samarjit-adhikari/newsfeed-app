package com.flipkart.test.FlipKartNewsFeed.configuration;

import com.flipkart.test.FlipKartNewsFeed.repositories.UserRepository;
import com.flipkart.test.FlipKartNewsFeed.service.NewsFeedUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new NewsFeedUserDetailsService(userRepository);
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //password matches os done here
    };
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); //it uses userDetails service

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        ProviderManager authenticationManager = new ProviderManager(Arrays.asList(authenticationProvider));
        return authenticationManager;
    }
}
