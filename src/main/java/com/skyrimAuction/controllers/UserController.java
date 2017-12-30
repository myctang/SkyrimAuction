package com.skyrimAuction.controllers;

import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", produces = "application/json")
    @ResponseBody
    public List<User> getItems(){
//        List<User> result = new ArrayList<UserModel>();
        List<User> got = userService.getUsers();
//        for (User user : got){
//            result.add(new UserModel(user));
//        }
        return got;
    }

    @PostMapping(value = "/users", consumes = "application/json")
    @ResponseBody
    public User add(@RequestBody User user){
        return userService.save(user);
    }

    @PostMapping(value = "/user", consumes = "application/json")
    @ResponseBody
    public User getUser(@RequestBody String name){
        User user = userService.getUserByName(name);
        for (Item item : user.getInventory()) {
            System.out.println(item);
        }
        return user;
    }

    @DeleteMapping(value="/users", consumes = "application/json")
    @ResponseBody
    public boolean remove(@RequestBody Long id){
        userService.removeUser(id);
        return true;
    }

    @PutMapping(value = "/users", consumes = "application/json")
    @ResponseBody
    public User update(@RequestBody User user){
        userService.updateUser(user);
        return user;
    }
}