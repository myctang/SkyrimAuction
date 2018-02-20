package com.skyrimAuction.dataBaseService.models;

import com.skyrimAuction.dataBaseService.entities.SellingItem;

import java.sql.Timestamp;

public class SellingItemModel {

    private long id;
    private ItemModel item;
    private int price;
    private Timestamp sellingStart;
    private long duration;

    public SellingItemModel() {
    }

    public SellingItemModel(SellingItem item) {
        this.id = item.getId();
        this.item = new ItemModel(item.getItem());
        this.price = item.getPrice();
        this.sellingStart = item.getSellingEnd();
        this.duration = item.getDuration();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getSellingStart() {
        return sellingStart;
    }

    public void setSellingStart(Timestamp sellingStart) {
        this.sellingStart = sellingStart;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
