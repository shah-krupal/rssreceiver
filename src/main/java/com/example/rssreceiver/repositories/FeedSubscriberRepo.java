package com.example.rssreceiver.repositories;

import com.example.rssreceiver.models.FeedSubscriber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedSubscriberRepo extends MongoRepository<FeedSubscriber, Integer> {
    FeedSubscriber findByFeedTitle(String title);
}
