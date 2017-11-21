package com.skyrimAuction.dataBaseService.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="quests")
public class Quest {
    @Id
    private long id;
    private String name;
    private boolean type;
    private String description;
    @ManyToMany
    @JoinTable(
            name="rewards",
            joinColumns = @JoinColumn(name = "quest", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item", referencedColumnName = "id")
    )
    private List<Item> rewards;
    @ManyToOne(optional = false)
    private Quest currentQuest;

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
