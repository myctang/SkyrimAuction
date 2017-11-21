package com.skyrimAuction.dataBaseService.services;

import com.skyrimAuction.dataBaseService.entities.Code;
import com.skyrimAuction.dataBaseService.entities.Item;
import io.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {

    @Autowired
    EbeanServer server;

    public List<Code> getCodes(){
        return server.find(Code.class).findList();
    }

    public Code getCode(long id){
        return server.find(Code.class, id);
    }

    public Code createCode(Code code){
        server.save(code);
        return code;
    }

    public Code updateCode(Code code){
        return createCode(code);
    }

    public Item getItem(long id){
        return server.find(Code.class, id).getItem();
    }

    public boolean removeCode(long id){
        server.delete(Code.class, id);
        return true;
    }
}
