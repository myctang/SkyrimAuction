package com.skyrimAuction.dataBaseService.entities;

import com.skyrimAuction.dataBaseService.models.SellingItemModel;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Данный класс представлет собой предмет, выставленный на аукцион.
 */
@Entity
@Table(name = "sellingItems")
public class SellingItem {
    /**
     * Код предмета.
     */
    @Id
    private long id;
    /**
     * Объект класа {@link Item}, объект данного класса представляет сам предмет и его свойства.
     */
    @ManyToOne(optional = false)
    private Item item;
    /**
     * Цена, с который предмет был выставлен.
     */
    private int price;
    private int buyNowPrice;

    private boolean finished;
    /**
     * Дата, когда предмет был выставлен.
     */
    @Column(columnDefinition = "datetime")
    private Timestamp sellingEnd;

    /**
     * Продолжительность аукциона.
     */
    private long duration;

    @ManyToOne(optional = false)
    private User holder;

    @ManyToOne
    private User lastBidder;

    /**
     * Инициализирует объект класса {@link SellingItem}.
     * Инициализирует поля {@link SellingItem#id}, {@link SellingItem#item}, {@link SellingItem#price}, {@link SellingItem#sellingEnd}.
     *
     * @param item - Объект класса {@link Item}
     */
    public SellingItem(SellingItemModel item) {
        this.id = item.getId();
        this.item = new Item(item.getItem());
        this.price = item.getPrice();
        this.sellingEnd = item.getSellingStart();
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
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

    public Timestamp getSellingEnd() {
        return sellingEnd;
    }

    public void setSellingEnd(Timestamp sellingEnd) {
        this.sellingEnd = sellingEnd;
    }

    public User getHolder() {
        return holder;
    }

    public void setHolder(User holder) {
        this.holder = holder;
    }

    public User getLastBidder() {
        return lastBidder;
    }

    public void setLastBidder(User lastBidder) {
        this.lastBidder = lastBidder;
    }

    public int getBuyNowPrice() {
        return buyNowPrice;
    }

    public void setBuyNowPrice(int buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
