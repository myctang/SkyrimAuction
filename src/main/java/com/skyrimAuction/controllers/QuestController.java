package com.skyrimAuction.controllers;

import com.skyrimAuction.dataBaseService.entities.Quest;
import com.skyrimAuction.dataBaseService.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuestController {

    @Autowired
    QuestService questService;

    @RequestMapping(value = "/quests", produces = "application/json")
    @ResponseBody
    public List<Quest> getCodes(){
        return questService.getQuests();
    }

    @PostMapping(value = "/quests", consumes = "application/json")
    @ResponseBody
    public Quest add(@RequestBody Quest quest){
        return questService.createQuest(quest);
    }

    @DeleteMapping(value="/quests", consumes = "application/json")
    @ResponseBody
    public boolean remove(@RequestBody long id){
        questService.removeQuest(id);
        return true;
    }

    @PutMapping(value = "/quests", consumes = "application/json")
    @ResponseBody
    public Quest update(@RequestBody Quest quest){
        questService.updateQuest(quest);
        return quest;
    }

}
