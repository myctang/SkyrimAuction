package com.skyrimAuction.controllers;

import com.skyrimAuction.dataBaseService.entities.SellingItem;
import com.skyrimAuction.dataBaseService.models.SellingItemModel;
import com.skyrimAuction.dataBaseService.services.SellingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SellingItemsController {
    @Autowired
    SellingItemService service;

    @GetMapping(value = "/api/get/sellingItems/", produces = "application/json")
    @ResponseBody
    public List<SellingItemModel> getItems(){
        List<SellingItemModel> result = new ArrayList<SellingItemModel>();
        List<SellingItem> got = service.getSellingItems();
        for (SellingItem item : got){
            result.add(new SellingItemModel(item));
        }
        return result;
    }

    @PostMapping(value = "/api/sellingItems", consumes = "application/json")
    @ResponseBody
    public SellingItemModel add(@RequestBody SellingItemModel item){
        return new SellingItemModel(service.save(new SellingItem(item)));
    }

    @DeleteMapping(value="/api/sellingItems", consumes = "application/json")
    @ResponseBody
    public boolean remove(@RequestBody Long id){
        service.removeSellingItem(id);
        return true;
    }

    @PutMapping(value = "/api/sellingItems", consumes = "application/json")
    @ResponseBody
    public SellingItem update(@RequestBody SellingItem sellingItem){
        service.updateSellingItem(sellingItem);
        return sellingItem;
    }
}
