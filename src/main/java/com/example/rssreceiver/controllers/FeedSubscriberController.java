package com.example.rssreceiver.controllers;

import com.example.rssreceiver.models.SubscribeRequest;
import com.example.rssreceiver.services.FeedSubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedsubscriber")
public class FeedSubscriberController {
    @Autowired
    private FeedSubscriberService feedSubscriberService ;

    @PostMapping("/subscribe")
    private String subscribe(@RequestBody SubscribeRequest subscribeRequest)
    {
        return feedSubscriberService.subscribe(subscribeRequest) ;
    }

}
