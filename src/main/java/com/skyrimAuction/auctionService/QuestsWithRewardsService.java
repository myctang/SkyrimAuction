package com.skyrimAuction.auctionService;

import com.skyrimAuction.dataBaseService.entities.InventoryItem;
import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.Quest;
import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.services.QuestService;
import com.skyrimAuction.dataBaseService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class QuestsWithRewardsService {

    private List<User> usersWithQuests;

    @Autowired
    QuestService questService;

    @Autowired
    UserService userService;

    @PostConstruct
    public void init() {
        this.usersWithQuests = userService.getUsersWithQuests();
    }

    @Scheduled(fixedRate = 500)
    public void checkEndOfQuests(){
        Iterator<User> it = usersWithQuests.iterator();
        while (it.hasNext()){
            User user = it.next();
            if (user.getQuest().getDuration() * 1000 + user.getStartOfQuest().getTime() <= System.currentTimeMillis()){
                System.out.println(user.getQuest().getDuration() * 1000 + user.getStartOfQuest().getTime() + " vs " + System.currentTimeMillis());
                System.out.println("duration " + user.getQuest().getDuration() * 1000 + " start " + user.getStartOfQuest().getTime());
                endQuest(user);
                it.remove();
            }
        }
        System.out.println("looped " + usersWithQuests.size() + " quests");
    }

    private void endQuest(User user){
//        List<InventoryItem> inventory = user.getInventory();
        for (Item item : user.getQuest().getRewards()){
            userService.addItem(item, user);
        }
        user.setQuest(null);
        userService.save(user);
        System.out.println("removed");
    }

    public void add(User user){
        System.out.println("added");
        usersWithQuests.add(user);
        System.out.println("size after add " + usersWithQuests.size());
    }
}
