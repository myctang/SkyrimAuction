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

    @Transactional
    public SellingItem save(SellingItem item){
        server.save(item);
        return item;
    }
}
