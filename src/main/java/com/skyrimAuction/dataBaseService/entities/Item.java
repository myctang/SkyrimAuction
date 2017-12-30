package com.skyrimAuction.dataBaseService.entities;

import com.skyrimAuction.dataBaseService.models.ItemModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Данный класс представляет собой предмет.
 *
 * @see User
 */
@Entity
@Table(name="items")
public class Item {
    /**
     * Код предмета.
     */
    @Id
    private long id;
    /**
     * Название предмета.
     */
    private String name;
    /**
     * Вес предмета.
     */
    private int weight;
    /**
     * Показатель защиты предмета.
     */
    private int defence;
    /**
     * Показатель атаки предмета.
     */
    private int damage;

    /**
     * Создает объект класса Item.
     */
    public Item() {
    }

    /**
     * Создает объект класса Item из объекта класса {@link Item} переданного в качестве аргумента.
     * Инициализирует поля {@link Item#name}, {@link Item#weight}, {@link Item#defence}, {@link Item#damage}.
     *
     * @param item - Объект класса {@link Item}
     */
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

    @Override
    public String toString() {
        return name;
    }
}
