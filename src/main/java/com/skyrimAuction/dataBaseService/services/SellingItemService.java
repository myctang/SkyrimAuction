package com.skyrimAuction.dataBaseService.services;

import com.skyrimAuction.dataBaseService.entities.SellingItem;
import com.skyrimAuction.dataBaseService.entities.User;
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

    public List<SellingItem> getActualLotsByUser(User user) {
        return server.find(SellingItem.class).where().eq("finished", "false").eq("holder_id", user.getId()).findList();
    }

    public List<SellingItem> getFinishedLotsByUser(User user) {
        return server.find(SellingItem.class).where().eq("finished", "true").eq("holder_id", user.getId()).findList();
    }

    public List<SellingItem> getTheMostExpensiveLots(int quantity) {
        if (quantity<=0)
            quantity = 1;
        server.find(SellingItem.class).orderBy("buy_now_price desc top " + quantity).findList();
        return null;
    }
    public List<SellingItem> getCurrentSellingItems(){
        return server.find(SellingItem.class).where().eq("finished", "false").findList();
    }

    public List<SellingItem> getMySellingItems(long id){
        return server.find(SellingItem.class).where().eq("holder_id", id).findList();
    }

    public SellingItem getSellingItem(long id){
        return server.find(SellingItem.class, id);
    }

    public void updateSellingItem(SellingItem item){
        server.save(item);
    }

    public SellingItem createSellingItem(SellingItem item){
        server.save(item);
        return item;
    }

    public SellingItem setPrice(long id, int price){
        SellingItem item = server.find(SellingItem.class, id);
        assert item != null;
        item.setPrice(price);
        server.save(item);
        return item;
    }

    public void removeSellingItem(long id){
        server.delete(SellingItem.class, id);
    }

    @Transactional
    public SellingItem save(SellingItem item){
        server.save(item);
        return item;
    }
}
