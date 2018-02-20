package com.skyrimAuction.dataBaseService.entities;

import com.skyrimAuction.dataBaseService.models.UserModel;
import io.ebean.annotation.DbArray;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.annotation.DbJson;
import io.ebean.annotation.DbJsonB;
import io.ebean.annotation.DbJsonType;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Данный класс представляет собой зарегистрированного пользователя.
 */
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"password", "roles"})
public class User implements UserDetails {
    /**
     * Код пользователя.
     */
    @Id
    private long id;

    /**
     * Имя пользователя.
     */
    @Column(unique = true)
    private String name;

    /**
     * Стартовое количество денег.
     */
    private long money;

    private String password;

    @DbArray
    @ElementCollection(targetClass = Role.class)
    @JsonIgnore
    private List<Role> roles;

//    /**
//     * Лист предметов, которые есть в наличии у пользователя.
//     *
//     * @see List
//     */
//    @OneToMany
//    private List<InventoryItem> inventory;

    /**
     * Квест которым пользователь в данный момент обладает.
     *
     * @see Quest
     */
    @ManyToOne
    private Quest quest;

    @Column(columnDefinition = "datetime")
    private Timestamp startOfQuest;

    private int telegramID;

    private boolean accountNonExpired, accountNonLocked, credentialsNonExpired, enabled;

    public User() {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    /**
     * Инициализирует объект класса {@link User}
     * Инициализирует поля {@link User#id}, {@link User#name}, {@link User#money}
     *
     * @param user - Объект класса {@link User}
     */
    public User(UserModel user) {
        this.id = user.getId();
        this.name = user.getName();
        this.money = user.getMoney();
    }

    public void grantAuthority(Role role) {
        if (roles == null) roles = new ArrayList<>();
        roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Object role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

//    public List<InventoryItem> getInventory() {
//        return inventory;
//    }
//
//    public void setInventory(List<InventoryItem> inventory) {
//        this.inventory = inventory;
//    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public Timestamp getStartOfQuest() {
        return startOfQuest;
    }

    public void setStartOfQuest(Timestamp startOfQuest) {
        this.startOfQuest = startOfQuest;
    }
    
    public long getTelegramID() {
        return telegramID;
    }

    public void setTelegramID(int telegramID) {
        this.telegramID = telegramID;
    }
}
