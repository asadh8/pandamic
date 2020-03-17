package com.entrue.pandamic.controller;

import com.entrue.pandamic.model.Item;
import com.entrue.pandamic.dao.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class IndexController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getIndexData() {
        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setItemName("Toilet Papers");
        item.setActive(true);
        itemService.createItem(item);

        return "Index data";
    }
}
