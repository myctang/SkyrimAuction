package com.skyrimAuction.controllers;

import com.skyrimAuction.dataBaseService.entities.Code;
import com.skyrimAuction.dataBaseService.services.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CodeController {

    @Autowired
    CodeService codeService;

    @RequestMapping(value = "/codes", produces = "application/json")
    @ResponseBody
    public List<Code> getCodes(){
        return codeService.getCodes();
    }

    @PostMapping(value = "/codes", consumes = "application/json")
    @ResponseBody
    public Code add(@RequestBody Code code){
        return codeService.createCode(code);
    }

    @DeleteMapping(value="/codes", consumes = "application/json")
    @ResponseBody
    public boolean remove(@RequestBody Long id){
        codeService.removeCode(id);
        return true;
    }

    @PutMapping(value = "/codes", consumes = "application/json")
    @ResponseBody
    public Code update(@RequestBody Code code){
        codeService.updateCode(code);
        return code;
    }

}
