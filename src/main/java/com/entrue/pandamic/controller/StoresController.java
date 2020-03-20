package com.entrue.pandamic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoresController {

    public ResponseEntity<?> getAvailableStores() {
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }
}
