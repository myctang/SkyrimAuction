package com.skyrimAuction.dataBaseService.models;

import com.skyrimAuction.dataBaseService.entities.Item;

public class ItemModel {
    private long id;
    private String name;
    private int weight;
    private int defence;
    private int damage;

    public ItemModel(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.weight = item.getWeight();
        this.defence = item.getDefence();
        this.damage = item.getDamage();
    }

    public ItemModel() {
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


}
