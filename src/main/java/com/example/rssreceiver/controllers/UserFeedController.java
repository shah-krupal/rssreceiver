package com.example.rssreceiver.controllers;

import com.example.rssreceiver.models.SubscribeRequest;
import com.example.rssreceiver.services.UserFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFeedController {
    @Autowired
    UserFeedService userFeedService ;
    @PostMapping("/subscribe")
    public String subscribe(@RequestBody SubscribeRequest subscribeRequest)
    {
        return userFeedService.subscribe(subscribeRequest) ;
    }
}
