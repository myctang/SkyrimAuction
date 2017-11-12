package com.skyrimAuction.dataBaseService.controllers;

import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.models.ItemModel;
import com.skyrimAuction.dataBaseService.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/items", produces = "application/json")
    @ResponseBody
    public List<ItemModel> getItems(){
        List<ItemModel> result = new ArrayList<ItemModel>();
        List<Item> got = itemService.getItems();
        for (Item item : got){
            result.add(new ItemModel(item));
        }
        return result;
    }

    @PostMapping(value = "/items", consumes = "application/json")
    @ResponseBody
    public ItemModel add(@RequestBody ItemModel item){
        return new ItemModel(itemService.save(new Item(item)));
    }

}
