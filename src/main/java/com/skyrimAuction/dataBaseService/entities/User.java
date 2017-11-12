package com.skyrimAuction.dataBaseService.entities;

import com.skyrimAuction.dataBaseService.models.UserModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    private long id;
    private String name;
    private long money;
    @ManyToMany
    @JoinTable(
            name = "inventory",
            joinColumns = @JoinColumn(name = "owner", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item", referencedColumnName = "id")
    )
    List<Item> inventory = new ArrayList<Item>();

    public User(UserModel user) {
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

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }
}
