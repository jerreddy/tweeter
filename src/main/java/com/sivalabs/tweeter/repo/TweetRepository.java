package com.sivalabs.tweeter.repo;

import com.sivalabs.tweeter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
