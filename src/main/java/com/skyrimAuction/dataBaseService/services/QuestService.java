package com.skyrimAuction.dataBaseService.services;

import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.Quest;
import io.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestService {
    @Autowired
    EbeanServer server;

    public List<Quest> getQuests(){
        return server.find(Quest.class).findList();
    }

    public Quest getQuest(long id){
        return server.find(Quest.class, id);
    }

    public Quest createQuest(Quest quest){
        server.save(quest);
        return quest;
    }

    public Quest updateQuest(Quest quest){
        server.save(quest);
        return quest;
    }

    public boolean removeQuest(long id){
        server.delete(Quest.class, id);
        return true;
    }

    public List<Item> getRewards(long id){
        return server.find(Quest.class, id).getRewards();
    }
}
