package com.skyrimAuction.dataBaseService.services;

import com.skyrimAuction.dataBaseService.entities.SellingItem;
import io.ebean.EbeanServer;
import io.ebean.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellingItemService {
    public SellingItemService() {
    }

    @Autowired
    EbeanServer server;

    public List<SellingItem> getSellingItems(){
        return server.find(SellingItem.class).findList();
    }

    public SellingItem getSellingItem(long id){
        return server.find(SellingItem.class, id);
    }

    public SellingItem updateSellingItem(SellingItem item){
        server.save(item);
        return item;
    }

    public SellingItem createSellingItem(SellingItem item){
        server.save(item);
        return item;
    }

    public SellingItem setPrice(long id, int price){
        SellingItem item = server.find(SellingItem.class, id);
        item.setPrice(price);
        server.save(item);
        return item;
    }

    public boolean removeSellingItem(long id){
        server.delete(SellingItem.class, id);
        return true;
    }

    @Transactional
    public SellingItem save(SellingItem item){
        server.save(item);
        return item;
    }
}
