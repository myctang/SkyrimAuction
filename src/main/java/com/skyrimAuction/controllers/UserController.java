package com.skyrimAuction.controllers;

import com.skyrimAuction.dataBaseService.entities.InventoryItem;
import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", produces = "application/json")
    @ResponseBody
    public List<User> getUsers() {
        List<User> got = userService.getUsers();
        return got;
    }

    @RequestMapping(value = "/api/users/info", produces = "application/json")
    @ResponseBody
    public User getUserInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserByName(username);
    }

    @RequestMapping(value = "/api/user/inventory", produces = "application/json")
    @ResponseBody
    public List<InventoryItem> getInventory() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getInventoryByName(username);
    }

    @RequestMapping(value = "api/user/changeBio", produces = "application/json")
    @ResponseBody
    public User changeBio(@RequestBody User user) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User initialUser = userService.getUserByName(username);
        if (user.getFirstName() != null && !user.getFirstName().equals("")) {
            initialUser.setFirstName(user.getFirstName());
        }
        if (user.getSecondName() != null && !user.getSecondName().equals("")){
            initialUser.setSecondName(user.getSecondName());
        }
        initialUser.setTelegramID((int)user.getTelegramID());
        return userService.save(initialUser);
    }

    @PostMapping(value = "/users", consumes = "application/json")
    @ResponseBody
    public User add(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping(value = "/user", consumes = "application/json")
    @ResponseBody
    public User getUser(@RequestBody String name) {
        return userService.getUserByName(name);
    }

    @DeleteMapping(value = "/users", consumes = "application/json")
    @ResponseBody
    public boolean remove(@RequestBody Long id) {
        userService.removeUser(id);
        return true;
    }

    @PutMapping(value = "/users", consumes = "application/json")
    @ResponseBody
    public User update(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }
}