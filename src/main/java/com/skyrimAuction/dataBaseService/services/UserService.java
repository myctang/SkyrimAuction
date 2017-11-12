package com.skyrimAuction.dataBaseService.services;

import com.skyrimAuction.dataBaseService.entities.User;
import io.ebean.EbeanServer;
import io.ebean.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public UserService() {
    }

    @Autowired
    EbeanServer server;

    public List<User> getUsers(){
        return server.find(User.class).findList();
    }

    @Transactional
    public User save(User user){
        server.save(user);
        return user;
    }
}
