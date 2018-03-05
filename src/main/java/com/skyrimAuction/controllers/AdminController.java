package com.skyrimAuction.controllers;

import com.skyrimAuction.dataBaseService.entities.InventoryItem;
import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.services.ItemService;
import com.skyrimAuction.dataBaseService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @GetMapping("/check")
    public ResponseEntity<String> checkAdmin(){
        return new ResponseEntity<>("{\"status\":\"done\"}", HttpStatus.ACCEPTED);
    }

    @GetMapping("/users")
    public List<User> getUsersList(){
        return userService.getUsers();
    }

    @GetMapping("/items")
    public List<Item> getItemsList(){
        return itemService.getItems();
    }

    @PostMapping("/setItem")
    public ResponseEntity<Boolean> setItemToUser(int userId, int itemId){
        User userFromBase = userService.getUser(userId);
        Item itemFromBase = itemService.getItem(itemId);
        if (userFromBase == null || itemFromBase == null){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        userService.addItem(itemFromBase, userFromBase);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
