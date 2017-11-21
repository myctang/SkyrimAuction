package com.skyrimAuction.dataBaseService.entities;

import com.skyrimAuction.dataBaseService.models.UserModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс представляет собой зарегистрированного пользователя.
 */
@Entity
@Table(name="users")
public class User {
    /**
     * Код пользователя.
     */
    @Id
    private long id;
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Стартовое количество денег.
     */
    private long money;
    /**
     * Лист предметов, которые есть в наличии у пользователя.
     *
     * @see List
     */
    @ManyToMany
    @JoinTable(
            name = "inventory",
            joinColumns = @JoinColumn(name = "owner", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item", referencedColumnName = "id")
    )
    private List<Item> inventory;
    /**
     * Квест которым пользователь в данный момент обладает.
     *
     * @see Quest
     */
    @ManyToOne
    private Quest quest;

    /**
     * Инициализирует объект класса {@link User}
     * Инициализирует поля {@link User#id}, {@link User#name}, {@link User#money}
     *
     * @param user - Объект класса {@link User}
     */
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

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }
}
