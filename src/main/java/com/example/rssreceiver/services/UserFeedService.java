package com.example.rssreceiver.services;

import com.example.rssreceiver.models.SubscribeRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserFeedService {
    String subscribe(SubscribeRequest subscribeRequest);
}
