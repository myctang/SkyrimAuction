package com.skyrimAuction.dataBaseService.models;

import com.skyrimAuction.dataBaseService.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

    private long id;
    private String name;
    private long money;
    private int telegramID;

    public UserModel() {
    }

    public UserModel(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.money = user.getMoney();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getTelegramID() {
        return telegramID;
    }

    public void setTelegramID(int telegramID) {
        this.telegramID = telegramID;
    }

}
