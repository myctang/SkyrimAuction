package com.skyrimAuction.securityService;

import com.skyrimAuction.dataBaseService.entities.InventoryItem;
import com.skyrimAuction.dataBaseService.entities.Item;
import com.skyrimAuction.dataBaseService.entities.Role;
import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.services.InventoryService;
import com.skyrimAuction.dataBaseService.services.ItemService;
import com.skyrimAuction.dataBaseService.services.UserService;
import io.ebean.annotation.Transactional;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AccountService implements UserDetailsService{

    private Logger logger = Logger.getLogger(AccountService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    InventoryService inventoryService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByName(s);
        if (user != null){
            return user;
        }else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
        }
    }

    public User findAccountByUsername(String username) throws UsernameNotFoundException{
        User user = userService.getUserByName(username);
        if (user != null){
            return user;
        }else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
    }

    public User registerUser(User account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.grantAuthority(Role.ROLE_USER);
        User userFromDb = userService.save( account );
        inventoryService.createItem(new InventoryItem(itemService.getItem(19), userFromDb, 1));
        inventoryService.createItem(new InventoryItem(itemService.getItem(20), userFromDb, 1));
        inventoryService.createItem(new InventoryItem(itemService.getItem(21), userFromDb, 1));
        inventoryService.createItem(new InventoryItem(itemService.getItem(54), userFromDb, 1));
//        account.setInventory(defaultInventory);
        account.setMoney(150);
        return userService.save( userFromDb );
    }

    @Transactional
    public void removeAuthenticatedAccount() throws UsernameNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User acct = findAccountByUsername(username);
        userService.removeUser(acct.getId());

    }
}
