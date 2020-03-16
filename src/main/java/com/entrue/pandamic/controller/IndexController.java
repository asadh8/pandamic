package com.entrue.pandamic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getIndexData() {
        return "Index data";
    }
}
