package com.skyrimAuction.dataBaseService.services;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EbeanFactoryBean implements FactoryBean<EbeanServer> {

    public EbeanFactoryBean() {
    }

    @Autowired
    CurrentUser currentUser;

    @Override
    public EbeanServer getObject() throws Exception {
        ServerConfig config = new ServerConfig();

        config.loadFromProperties();
        config.setCurrentUserProvider(currentUser);

        config.setDefaultServer(true);
        config.setRegister(true);

        return EbeanServerFactory.create(config);
    }

    @Override
    public Class<?> getObjectType() {
        return EbeanServer.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
