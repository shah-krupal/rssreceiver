package com.example.rssreceiver.services;

import com.example.rssreceiver.models.SubscribeRequest;
import org.springframework.stereotype.Service;

@Service
public interface FeedSubscriberService {
    String subscribe(SubscribeRequest subscribeRequest);

}
