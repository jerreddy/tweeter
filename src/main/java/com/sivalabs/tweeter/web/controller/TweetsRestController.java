package com.sivalabs.tweeter.web.controller;

import com.sivalabs.tweeter.entity.Tweet;
import com.sivalabs.tweeter.repo.TweetRepository;
import com.sivalabs.tweeter.repo.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetsRestController {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetsRestController(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Tweet> allTweets() {
        return tweetRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void saveTweet(@RequestBody Tweet tweet) {
        tweet.setId(null);
        tweet.setCreatedBy(loginUser());
        tweetRepository.save(tweet);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteTweet(@PathVariable Long id) {
        tweetRepository.deleteById(id);
    }

    private com.sivalabs.tweeter.entity.User loginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User securityUser = (User) authentication.getPrincipal();
            return userRepository.findByEmail(securityUser.getUsername()).get();
        }
        return null;
    }
}
