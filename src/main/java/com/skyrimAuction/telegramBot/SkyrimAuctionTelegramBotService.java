package com.skyrimAuction.telegramBot;

import com.skyrimAuction.dataBaseService.services.ItemService;
import com.skyrimAuction.dataBaseService.services.QuestService;
import com.skyrimAuction.dataBaseService.services.SellingItemService;
import com.skyrimAuction.dataBaseService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkyrimAuctionTelegramBotService {

    private static UserService userService;
    private static ItemService itemService;
    private static SellingItemService sellingItemService;

    @Autowired
    public SkyrimAuctionTelegramBotService(UserService userService, ItemService itemService, SellingItemService sellingItemService, QuestService questService) {
        SkyrimAuctionTelegramBotService.userService = userService;
        SkyrimAuctionTelegramBotService.itemService = itemService;
        SkyrimAuctionTelegramBotService.sellingItemService = sellingItemService;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static ItemService getItemService() {
        return itemService;
    }

    public static SellingItemService getSellingItemService() {
        return sellingItemService;
    }

}
