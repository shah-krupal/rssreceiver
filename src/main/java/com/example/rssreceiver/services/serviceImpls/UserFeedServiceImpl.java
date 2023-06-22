package com.example.rssreceiver.services.serviceImpls;

import com.example.rssreceiver.models.FeedSubscriber;
import com.example.rssreceiver.models.SubscribeRequest;
import com.example.rssreceiver.models.UserFeed;
import com.example.rssreceiver.models.UserInfo;
import com.example.rssreceiver.repositories.UserInfoRepo;
import com.example.rssreceiver.services.UserFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFeedServiceImpl implements UserFeedService {
    @Autowired
    MongoTemplate mongoTemplate ;
    @Override
    public String subscribe(SubscribeRequest subscribeRequest) {
        Query confirmationQuery = new Query() ;
        confirmationQuery.addCriteria(Criteria.where("emailId").is(subscribeRequest.getEmailId())) ;
        try{
            List<UserInfo> queryAns = mongoTemplate.find(confirmationQuery,UserInfo.class) ;
            if(queryAns.isEmpty())
            {
                return "Sorry! Please signup first" ;
            }
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage() ;
        }

        Query query = new Query() ;
        query.addCriteria(Criteria.where("emailId").is(subscribeRequest.getEmailId())) ;

        Update update = new Update() ;
        update.addToSet("feedTitles").each(subscribeRequest.getFeedTitle()) ;

        try{
            mongoTemplate.upsert(query, update, UserFeed.class) ;
            return "subscription added successfully" ;
        }catch(Exception e){
            e.printStackTrace();
            return "Failed to add subscription" ;
        }
    }
}
