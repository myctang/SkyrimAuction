package com.skyrimAuction.dataBaseService.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="inventoryItems")
public class InventoryItem {

    public InventoryItem(Item item, User user, int quantity) {
        this.item = item;
        this.user = user;
        this.quantity = quantity;
    }

    public InventoryItem() {
    }

    @Id private long id;

    @ManyToOne
    private Item item;

    @ManyToOne
    private User user;

    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
