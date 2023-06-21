package com.example.rssreceiver.services.serviceImpls;

import com.example.rssreceiver.models.FeedSubscriber;
import com.example.rssreceiver.models.SubscribeRequest;
import com.example.rssreceiver.services.FeedSubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;



@Service
public class FeedSubscriberServiceImpl implements FeedSubscriberService {
    @Autowired
    private MongoTemplate mongoTemplate ;
    @Override
    public String subscribe(SubscribeRequest subscribeRequest) {
        Query query = new Query() ;
        query.addCriteria(Criteria.where("feedId").is(subscribeRequest.getFeedId())) ;

        Update update = new Update() ;
        update.addToSet("emailIds").each(subscribeRequest.getEmailId()) ;

        try{
            mongoTemplate.upsert(query, update, FeedSubscriber.class) ;
            return "subscription added successfully" ;
        }catch(Exception e){
            e.printStackTrace();
            return "Failed to add subscription" ;
        }

    }
}
