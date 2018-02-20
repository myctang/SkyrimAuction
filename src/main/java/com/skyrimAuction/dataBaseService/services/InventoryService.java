package com.skyrimAuction.dataBaseService.services;

import com.skyrimAuction.dataBaseService.entities.InventoryItem;
import io.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    public InventoryService() {
    }

    @Autowired
    EbeanServer ebeanServer;

    public boolean updateItem(InventoryItem item){
        ebeanServer.save(item);
        return true;
    }

    public boolean removeItem(long id){
        ebeanServer.delete(InventoryItem.class, id);
        return true;
    }

    public InventoryItem createItem(InventoryItem item){
        ebeanServer.save(item);
        return item;
    }
}
