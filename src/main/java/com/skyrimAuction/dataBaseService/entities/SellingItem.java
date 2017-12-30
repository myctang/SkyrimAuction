package com.skyrimAuction.dataBaseService.entities;

import com.skyrimAuction.dataBaseService.models.SellingItemModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Данный класс представлет собой предмет, выставленный на аукцион.
 */
@Entity
@Table(name="sellingItems")
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
    /**
     * Дата, когда предмет был выставлен.
     */
    private Date sellingStart;

    /**
     * Продолжительность аукциона.
     */
    private long duration;

    /**
     * Инициализирует объект класса {@link SellingItem}.
     * Инициализирует поля {@link SellingItem#id}, {@link SellingItem#item}, {@link SellingItem#price}, {@link SellingItem#sellingStart}.
     *
     * @param item - Объект класса {@link Item}
     */
    public SellingItem(SellingItemModel item) {
        this.id = item.getId();
        this.item = new Item(item.getItem());
        this.price = item.getPrice();
        this.sellingStart = item.getSellingStart();
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

    public Date getSellingStart() {
        return sellingStart;
    }

    public void setSellingStart(Date sellingStart) {
        this.sellingStart = sellingStart;
    }

}
