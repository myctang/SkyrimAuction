package com.skyrimAuction.controllers;

import com.skyrimAuction.dataBaseService.entities.InventoryItem;
import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.services.ItemService;
import com.skyrimAuction.dataBaseService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/userItem")
    public List<InventoryItem> getUserItems(@RequestParam int userId){
        return userService.getInventory(userService.getUser(userId));
    }

    @PostMapping("/setItem")
    public ResponseEntity<Boolean> setItemToUser(@RequestParam int userId, @RequestParam int itemId){
        User userFromBase = userService.getUser(userId);
        Item itemFromBase = itemService.getItem(itemId);
        if (userFromBase == null || itemFromBase == null){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        userService.addItem(itemFromBase, userFromBase);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
