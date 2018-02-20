package com.skyrimAuction.auctionService;

import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.SellingItem;
import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.services.ItemService;
import com.skyrimAuction.dataBaseService.services.SellingItemService;
import com.skyrimAuction.dataBaseService.services.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

@Service
public class AuctionItemsService {

    private List<SellingItem> sellingItems;

    @Autowired
    private SellingItemService sellingItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ObjectMapper mapper;

    @PostConstruct
    public void init(){
        this.sellingItems = sellingItemService.getCurrentSellingItems();
    }

    @Scheduled(fixedRate = 500)
    public void checkEndOfAuction(){
        Iterator<SellingItem> it = sellingItems.iterator();
        while (it.hasNext()){
            SellingItem sellingItem = it.next();
            if (sellingItem.getSellingEnd().before(new Timestamp(System.currentTimeMillis()))){
                endOfAuction(sellingItem);
                it.remove();
            }
        }
        System.out.println("looped " + sellingItems.size() + " items");
    }

    private void endOfAuction(SellingItem sellingItem){
        SellingItem fromBase = sellingItemService.getSellingItem(sellingItem.getId());
        fromBase.setFinished(true);
        sellingItemService.save(fromBase);
        User winner;
        if (fromBase.getLastBidder() == null){
            winner = fromBase.getHolder();
        }else{
            winner = fromBase.getLastBidder();
            winner.setMoney(winner.getMoney() + sellingItem.getPrice());
        }
        Item wonItem = itemService.getItem(sellingItem.getItem().getId());
        userService.addItem(wonItem, winner);
        userService.updateUser(winner);
        try{
            simpMessagingTemplate.send("/updateItems", MessageBuilder.withPayload(mapper.writeValueAsString(sellingItemService.getCurrentSellingItems()).getBytes("UTF-8")).build());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void add(SellingItem item){
        sellingItems.add(item);
    }

}
