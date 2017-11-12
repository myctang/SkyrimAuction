package com.skyrimAuction.dataBaseService.controllers;

import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.models.UserModel;
import com.skyrimAuction.dataBaseService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", produces = "application/json")
    @ResponseBody
    public List<UserModel> getItems(){
        List<UserModel> result = new ArrayList<UserModel>();
        List<User> got = userService.getUsers();
        for (User user : got){
            result.add(new UserModel(user));
        }
        return result;
    }

    @PostMapping(value = "/users", consumes = "application/json")
    @ResponseBody
    public UserModel add(@RequestBody UserModel user){
        return new UserModel(userService.save(new User(user)));
    }
}
