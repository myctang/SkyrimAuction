package com.skyrimAuction.dataBaseService.services;

import com.skyrimAuction.dataBaseService.entities.Item;
import io.ebean.EbeanServer;
import io.ebean.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    public ItemService() {
    }

    @Autowired
    EbeanServer server;

    public List<Item> getItems(){
        return server.find(Item.class).findList();
    }

    @Transactional
    public Item save(Item item){
        server.save(item);
        return item;
    }
}
