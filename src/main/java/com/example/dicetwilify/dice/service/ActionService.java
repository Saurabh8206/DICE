package com.example.dicetwilify.dice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class ActionService {
    @Autowired
    private Twitter twitter;

    /**
     * This method fetch all the users commanding with the param
     * @param param
     * @param numberOfRecords
     * @return
     * @throws TwitterException
     */
    public ResponseList<User>  searchUsersWithParam(String param,int numberOfRecords) throws TwitterException {
        try{
            ResponseList<User> users = twitter.searchUsers(param, numberOfRecords);
            return users;
        }catch (Exception e) {
            log.error("Exception in searching user with the params - "+param +" -> " + e);
          return null;
        }
    }

    /**
     * This method fetch the tweets of user
     * @param userId
     * @return
     * @throws TwitterException
     */
    public List<Status> fetchTweetsForUser(Long userId) throws TwitterException {
        try{
            ResponseList<Status> userTweets = twitter.getUserTimeline(userId);
            return  userTweets;
        }catch (Exception e) {
            log.error("Exception in fetching users tweets status - " + e);
            return new ArrayList<>();
        }
    }
}
