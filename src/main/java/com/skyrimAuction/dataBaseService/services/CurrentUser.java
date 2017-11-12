package com.skyrimAuction.dataBaseService.services;

import io.ebean.config.CurrentUserProvider;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser implements CurrentUserProvider {
    public CurrentUser() {
    }

    @Override
    public Object currentUser() {
        return "myctang";
    }
}
