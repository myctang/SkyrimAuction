package com.skyrimAuction.dataBaseService.entities;

import com.skyrimAuction.dataBaseService.models.ItemModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class Item{
    @Id
    private long id;
    private String name;
    private int weight;
    private int defence;
    private int damage;

    public Item() {
    }

    public Item(ItemModel item) {
        this.name = item.getName();
        this.weight = item.getWeight();
        this.defence = item.getDefence();
        this.damage = item.getDamage();
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
