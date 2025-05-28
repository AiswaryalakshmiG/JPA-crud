package com.example.JpaCrud.ratelimit;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

@Component
public class RateLimiterService {
    private Bucket bucket;

    @PostConstruct
    public void setupBucket() {
        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1)));
        bucket = Bucket.builder().addLimit(limit).build();
    }

    public void validateRequest() {
        if (!bucket.tryConsume(1)) {
            throw new RuntimeException("Too many requests! Please wait and try again after 1 minute.");
        }
    }
}