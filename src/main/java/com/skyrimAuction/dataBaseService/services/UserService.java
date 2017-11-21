package com.skyrimAuction.dataBaseService.services;

import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.Quest;
import com.skyrimAuction.dataBaseService.entities.User;
import io.ebean.EbeanServer;
import io.ebean.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
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

    public User getUser(long id){return server.find(User.class, id);}

    public List<Item> getInventory(long id){
        return server.find(User.class, id).getInventory();
    }

    public User addItem(Item item, long id){
        User user = server.find(User.class, id);
        user.getInventory().add(item);
        server.save(user);
        return user;
    }

    public User addQuest(Quest quest, long id){
        User user = server.find(User.class, id);
        user.setQuest(quest);
        server.save(user);
        return user;
    }

    public User createUser(User user){
        server.save(user);
        return user;
    }

    public User deleteQuest(long id){
        return addQuest(null, id);
    }

    public User updateUser(User user){
        server.save(user);
        return user;
    }

    public boolean removeUser(long id){
        server.delete(User.class, id);
        return true;
    }

    @Transactional
    public User save(User user){
        server.save(user);
        return user;
    }
}
