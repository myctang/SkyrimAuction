package com.skyrimAuction.dataBaseService.services;

import com.skyrimAuction.dataBaseService.entities.InventoryItem;
import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.Quest;
import com.skyrimAuction.dataBaseService.entities.User;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public UserService() {
    }

    @Autowired
    EbeanServer server;

    @Autowired
    InventoryService inventoryService;

    public List<User> getUsers() {
        return server.find(User.class).findList();
    }

    public User getUser(long id) {
        return server.find(User.class, id);
    }

    public List<InventoryItem> getInventoryByName(String name) {
        String sql = "select inventoryItems.id, item_id, user_id, quantity from inventoryItems join users on user_id=users.id where users.name=:username";
        List<InventoryItem> inventoryItem = Ebean.findNative(InventoryItem.class, sql).setParameter("username", name)
                .findList();
        return inventoryItem;
    }

    public boolean checkQuestByName(String name) {
        User user = server.find(User.class).where().eq("name", name).findOne();
        return user != null && user.getQuest() != null;
    }

    public boolean getOneFromInventory(Item item, String username) {
        String sql = String.format("select inventoryItems.id, item_id, user_id, quantity from inventoryItems join users on user_id=users.id where users.name='%s' AND item_id=%s", username, item.getId());
        InventoryItem inventoryItem = Ebean.findNative(InventoryItem.class, sql).findOne();
        if (inventoryItem == null){
            return false;
        }
        if (inventoryItem.getQuantity() == 1) {
            inventoryService.removeItem(inventoryItem.getId());
            return true;
        }
        inventoryItem.setQuantity(inventoryItem.getQuantity() - 1);
        inventoryService.updateItem(inventoryItem);
        return true;
    }

    public User addItem(Item item, User user) {
        String sql = String.format("select inventoryItems.id, item_id, user_id, quantity from inventoryItems join users on user_id=users.id where users.name='%s' AND item_id=%s", user.getName(), item.getId());
        InventoryItem inventoryItem = Ebean.findNative(InventoryItem.class, sql).findOne();
        if (inventoryItem == null){
            inventoryService.createItem(new InventoryItem(item, user, 1));
        }else{
            inventoryItem.setQuantity(inventoryItem.getQuantity() + 1);
            inventoryService.updateItem(inventoryItem);
        }
        return user;
    }

    public User addQuest(Quest quest, long id) {
        User user = server.find(User.class, id);
        user.setQuest(quest);
        server.save(user);
        return user;
    }

    public User getUserByName(String name) {
        return server.find(User.class).where().eq("name", name).findOne();
    }

    public List<User> getUsersWithQuests() {
        return server.find(User.class).where().isNotNull("quest_id").findList();
    }

    public User createUser(User user) {
        server.save(user);
        return user;
    }

    public User deleteQuest(long id) {
        return addQuest(null, id);
    }

    public User updateUser(User user) {
        server.update(user);
        return user;
    }

    public boolean removeUser(long id) {
        server.delete(User.class, id);
        return true;
    }

    @Transactional
    public User save(User user) {
        server.save(user);
        return user;
    }
}
