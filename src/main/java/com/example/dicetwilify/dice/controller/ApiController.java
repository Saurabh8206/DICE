package com.example.dicetwilify.dice.controller;

import com.example.dicetwilify.dice.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ActionService service;
    private final Twitter twitter;


    @Autowired
    public ApiController(ActionService service, Twitter twitter) {
        this.service = service;
        this.twitter = twitter;
    }

    @GetMapping("/users/{query}")
    public ResponseEntity<?> searchUsers(@PathVariable String query) {
        try {
        List<User> users = service.searchUsersWithParam(query, 20);
        return ResponseEntity.ok(users);
        } catch (TwitterException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @GetMapping("/tweets/{userId}")
    public ResponseEntity<?> getUserTweets(@PathVariable Long userId) {
        try {
            ResponseList<Status> userTimeline = twitter.getUserTimeline(userId);
            return ResponseEntity.ok(userTimeline);
        } catch (TwitterException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }
}