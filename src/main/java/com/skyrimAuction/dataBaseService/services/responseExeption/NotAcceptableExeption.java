package com.skyrimAuction.dataBaseService.services.responseExeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableExeption extends RuntimeException {
    public NotAcceptableExeption() {
        super();
    }
}
