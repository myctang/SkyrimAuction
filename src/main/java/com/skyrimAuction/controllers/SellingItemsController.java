package com.skyrimAuction.controllers;

import com.skyrimAuction.auctionService.AuctionItemsService;
import com.skyrimAuction.dataBaseService.entities.SellingItem;
import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.services.ItemService;
import com.skyrimAuction.dataBaseService.services.SellingItemService;
import com.skyrimAuction.dataBaseService.services.UserService;
import com.skyrimAuction.dataBaseService.services.responseExeption.NotAcceptableExeption;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class SellingItemsController {
    @Autowired
    SellingItemService sellingItemService;
    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;
    @Autowired
    AuctionItemsService auctionItemsService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private ObjectMapper mapper;

    @GetMapping(value = "/api/get/sellingItems", produces = "application/json")
    @ResponseBody
    public List<SellingItem> getItems() {
        return sellingItemService.getCurrentSellingItems();
    }

    @GetMapping(value = "/api/get/mySellingItems", produces = "application/json")
    @ResponseBody
    public List<SellingItem> getMyItems() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByName(username);
        if (user == null){
            throw new NotAcceptableExeption();
        }
        return sellingItemService.getMySellingItems(user.getId());
    }

    @PostMapping(value = "/api/sellingItem/create", consumes = "application/json")
    @ResponseBody
    public SellingItem createAuction(@RequestBody SellingItem item) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userService.getOneFromInventory(item.getItem(), username)) {
            item.setSellingEnd(new Timestamp(System.currentTimeMillis() + item.getDuration() * 1000));
            item.setHolder(userService.getUserByName(username));
            item.setFinished(false);
            SellingItem itemFromBase = sellingItemService.save(item);
            auctionItemsService.add(itemFromBase);
            try {
                simpMessagingTemplate.send("/updateItems", MessageBuilder.withPayload(mapper.
                        writeValueAsString(sellingItemService.getCurrentSellingItems()).getBytes("UTF-8")).build());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return itemFromBase;
        } else
            throw new NotAcceptableExeption();
    }

    @PostMapping(value = "/api/sellingItems/bid", consumes = "application/json")
    @ResponseBody
    public SellingItem makeBid(@RequestBody SellingItem item) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User bidder = userService.getUserByName(username);
        SellingItem itemFromBase = sellingItemService.getSellingItem(item.getId());

        if (item.getPrice() > bidder.getMoney() || itemFromBase.getPrice() > item.getPrice() || item.getPrice() <= 0 || item.getPrice() >= itemFromBase.getBuyNowPrice()) {
            throw new NotAcceptableExeption();
        } else {
            if (itemFromBase.getLastBidder() != null) {
                itemFromBase.getLastBidder().setMoney(itemFromBase.getLastBidder().getMoney() + itemFromBase.getPrice());
            }
            itemFromBase.setPrice(item.getPrice());
            bidder.setMoney(bidder.getMoney() - item.getPrice());
            itemFromBase.setLastBidder(bidder);
            itemFromBase = sellingItemService.save(itemFromBase);
            try {
                simpMessagingTemplate.send("/updateItems", MessageBuilder.withPayload(mapper.
                        writeValueAsString(sellingItemService.getCurrentSellingItems()).getBytes("UTF-8")).build());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return itemFromBase;
        }
    }

    @PostMapping(value = "/api/sellingItems/buyNow", consumes = "application/json")
    @ResponseBody
    public List<SellingItem> buyNow(@RequestBody SellingItem item){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User buyer = userService.getUserByName(username);
        SellingItem itemFromBase = sellingItemService.getSellingItem(item.getId());
        if (itemFromBase == null || itemFromBase.getBuyNowPrice() > buyer.getMoney()){
            throw new NotAcceptableExeption();
        }
        sellingItemService.removeSellingItem(item.getId());
        userService.addItem(itemFromBase.getItem(), buyer);
        try {
            simpMessagingTemplate.send("/updateItems", MessageBuilder.withPayload(mapper.
                    writeValueAsString(sellingItemService.getCurrentSellingItems()).getBytes("UTF-8")).build());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sellingItemService.getCurrentSellingItems();
    }

    @DeleteMapping(value = "/api/sellingItems", consumes = "application/json")
    @ResponseBody
    public boolean remove(@RequestBody Long id) {
        sellingItemService.removeSellingItem(id);
        return true;
    }

    @PutMapping(value = "/api/sellingItems", consumes = "application/json")
    @ResponseBody
    public SellingItem update(@RequestBody SellingItem sellingItem) {
        sellingItemService.updateSellingItem(sellingItem);
        return sellingItem;
    }

    @MessageMapping("/updateItems")
    public void receiveColor(String message) {
        System.out.println(message);
    }
}
