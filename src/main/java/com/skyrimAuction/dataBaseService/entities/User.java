package com.skyrimAuction.dataBaseService.entities;

import com.skyrimAuction.dataBaseService.models.UserModel;
import io.ebean.annotation.DbArray;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Данный класс представляет собой зарегистрированного пользователя.
 */
@Entity
@Table(name="users")
public class User implements UserDetails {
    /**
     * Код пользователя.
     */
    @Id
    private long id;

    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Стартовое количество денег.
     */
    private long money;

    @JsonIgnore
    private String password;

    @DbArray
    @ElementCollection(targetClass = Role.class)
    private List<Role> roles;

    /**
     * Лист предметов, которые есть в наличии у пользователя.
     *
     * @see List
     */
    @ManyToMany
    @JoinTable(
            name = "inventory",
            joinColumns = @JoinColumn(name = "owner", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item", referencedColumnName = "id")
    )
    private List<Item> inventory;

    /**
     * Квест которым пользователь в данный момент обладает.
     *
     * @see Quest
     */
    @ManyToOne
    private Quest quest;

    private long telegramID;

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

    public void grantAuthority(Role role){
        if (roles == null) roles = new ArrayList<>();
        roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Object role : roles){
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
//        roles.forEach(role -> );
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

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public long getTelegramID() {
        return telegramID;
    }

    public void setTelegramID(long telegramID) {
        this.telegramID = telegramID;
    }
}
