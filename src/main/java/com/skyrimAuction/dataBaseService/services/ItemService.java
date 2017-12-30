package com.skyrimAuction.dataBaseService.services;

import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.SellingItem;
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

    public Item getItem(long id){return server.find(Item.class, id);}

    public boolean updateItem(Item item){
        server.save(item);
        return true;
    }

    public Item createItem(Item item){
        server.save(item);
        return item;
    }

    public boolean removeItem(long id){
        server.delete(SellingItem.class, id);
        return true;
    }

    @Transactional
    public Item save(Item item){
        server.save(item);
        return item;
    }
}
