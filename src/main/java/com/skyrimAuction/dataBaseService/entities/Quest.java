package com.skyrimAuction.dataBaseService.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Данный класс представляет собой Квест. Квестами могут владеть польщователи, за успешное выполнение квеста назанчен определенный {@link List} наград, в роли которых выступают {@link Item}.
 *
 */
@Entity
@Table(name="quests")
public class Quest {
    /**
     * Код квеста.
     */
    @Id
    private long id;
    /**
     * Название квеста.
     */
    private String name;
    /**
     * Тип квеста. Сюжетный или нет.
     */
    private boolean type;
    /**
     * Описание квеста.
     */
    private String description;
    /**
     * Лист предметов, которые можно получить за успешное выполнение квеста.
     *
     * @see List
     */
    @ManyToMany
    @JoinTable(
            name="rewards",
            joinColumns = @JoinColumn(name = "quest", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item", referencedColumnName = "id")
    )
    private List<Item> rewards;
    @ManyToOne(optional = false)

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

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getRewards() {
        return rewards;
    }

    public void setRewards(List<Item> rewards) {
        this.rewards = rewards;
    }
}
