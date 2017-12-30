package com.skyrimAuction.dataBaseService.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 Данный класс предсталяет собой "Промо-Код". Объекты данного класса представляют собой комбинацию для получения предмета.
 */
@Entity
@Table(name = "code")
public class Code {
    /**
     * Код промо-кода.
     */
    @Id
    private long id;
    /**
     * Комбинация на получение предмета.
     */
    private String code;
    /**
     * Описание просо-кода.
     */
    private String description;
    /**
     * Объект класса Item. Предмет, получаемый пользователем.
     *
     * @see Item
     */
    @ManyToOne(optional = false)
    private Item item;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
