package com.skyrimAuction.controllers;

import com.skyrimAuction.auctionService.QuestsWithRewardsService;
import com.skyrimAuction.dataBaseService.entities.Quest;
import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.services.QuestService;
import com.skyrimAuction.dataBaseService.services.UserService;
import com.skyrimAuction.dataBaseService.services.responseExeption.NotAcceptableExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class QuestController {

    @Autowired
    QuestService questService;

    @Autowired
    UserService userService;

    @Autowired
    QuestsWithRewardsService questsWithRewardsService;

    @RequestMapping(value = "/api/get/quests", produces = "application/json")
    @ResponseBody
    public List<Quest> getQuests() {
        return questService.getQuests();
    }

    @PostMapping(value = "/quests", consumes = "application/json")
    @ResponseBody
    public Quest add(@RequestBody Quest quest) {
        return questService.createQuest(quest);
    }

    @DeleteMapping(value = "/quests", consumes = "application/json")
    @ResponseBody
    public boolean remove(@RequestBody long id) {
        questService.removeQuest(id);
        return true;
    }

    @PutMapping(value = "/quests", consumes = "application/json")
    @ResponseBody
    public Quest update(@RequestBody Quest quest) {
        questService.updateQuest(quest);
        return quest;
    }

    @RequestMapping(value = "/api/quest/check", produces = "application/json")
    @ResponseBody
    public boolean checkQuest(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.checkQuestByName(username);
    }

    @PostMapping(value = "/api/quests/add", consumes = "application/json")
    @ResponseBody
    public User addQuestToUser(@RequestBody Quest quest){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByName(username);
        Quest questFromBase = questService.getQuest(quest.getId());
        if (user.getQuest() != null){
            throw new NotAcceptableExeption();
        }
        user.setQuest(questFromBase);
        user.setStartOfQuest(new Timestamp(System.currentTimeMillis()));
        userService.save(user);
        User userFromBase = userService.getUserByName(username);
        questsWithRewardsService.add(userFromBase);
        return userFromBase;
    }

}
