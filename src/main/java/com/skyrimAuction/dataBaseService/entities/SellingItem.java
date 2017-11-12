package com.skyrimAuction.dataBaseService.entities;

import com.skyrimAuction.dataBaseService.models.SellingItemModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="sellingItems")
public class SellingItem {
    @Id
    private long id;
    @ManyToOne(optional = false)
    private Item item;
    private int price;
    private Date sellingStart;
    private long duration;

    public SellingItem(SellingItemModel item) {
        this.id = item.getId();
        this.item = new Item(item.getItem());
        this.price = item.getPrice();
        this.sellingStart = item.getSellingStart();
        this.duration = item.getDuration();
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSellingStart() {
        return sellingStart;
    }

    public void setSellingStart(Date sellingStart) {
        this.sellingStart = sellingStart;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
