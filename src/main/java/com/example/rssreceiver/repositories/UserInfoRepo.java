package com.example.rssreceiver.repositories;

import com.example.rssreceiver.models.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends MongoRepository<UserInfo, String> {
//    static UserInfo findByEmailId(String emailId) ;
}
