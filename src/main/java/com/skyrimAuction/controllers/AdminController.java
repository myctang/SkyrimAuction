package com.skyrimAuction.controllers;

import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.services.ItemService;
import com.skyrimAuction.dataBaseService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @GetMapping("admin/check")
    public ResponseEntity<String> checkAdmin(){
        return new ResponseEntity<>("{\"status\":\"done\"}", HttpStatus.ACCEPTED);
    }

    @GetMapping("admin/users")
    public List<User> getUsersList(){
        return userService.getUsers();
    }

    @GetMapping("admin/items")
    public List<Item> getItemsList(){
        return itemService.getItems();
    }


}
