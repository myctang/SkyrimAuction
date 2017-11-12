package com.skyrimAuction.dataBaseService.controllers;

import com.skyrimAuction.dataBaseService.entities.SellingItem;
import com.skyrimAuction.dataBaseService.models.SellingItemModel;
import com.skyrimAuction.dataBaseService.services.SellingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SellingItemsController {
    @Autowired
    SellingItemService service;

    @RequestMapping(value = "/selling", produces = "application/json")
    @ResponseBody
    public List<SellingItemModel> getItems(){
        List<SellingItemModel> result = new ArrayList<SellingItemModel>();
        List<SellingItem> got = service.getSellingItems();
        for (SellingItem item : got){
            result.add(new SellingItemModel(item));
        }
        return result;
    }

    @PostMapping(value = "/selling", consumes = "application/json")
    @ResponseBody
    public SellingItemModel add(@RequestBody SellingItemModel item){
        return new SellingItemModel(service.save(new SellingItem(item)));
    }
}
