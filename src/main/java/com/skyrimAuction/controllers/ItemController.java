package com.skyrimAuction.controllers;

import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.models.ItemModel;
import com.skyrimAuction.dataBaseService.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/api/get/items", produces = "application/json")
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

    @DeleteMapping(value="/items", consumes = "application/json")
    @ResponseBody
    public boolean remove(@RequestBody Long id){
        itemService.removeItem(id);
        return true;
    }

    @PutMapping(value = "/items", consumes = "application/json")
    @ResponseBody
    public Item update(@RequestBody Item item){
        itemService.updateItem(item);
        return item;
    }

}