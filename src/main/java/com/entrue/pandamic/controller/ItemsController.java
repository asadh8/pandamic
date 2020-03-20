package com.entrue.pandamic.controller;

import com.entrue.pandamic.exception.InternalException;
import com.entrue.pandamic.service.ItemsManager;
import com.entrue.pandamic.view.request.item.CreateItemRequest;
import com.entrue.pandamic.view.response.GenericResponse;
import com.entrue.pandamic.view.response.item.ItemsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsController {

    private ItemsManager itemsManager;

    @Autowired
    public ItemsController(ItemsManager itemsManager) {
        this.itemsManager = itemsManager;
    }

    @RequestMapping(path = "/items/create", method = RequestMethod.POST)
    public ResponseEntity<?> createItem(@RequestBody CreateItemRequest request) {
        GenericResponse response = new GenericResponse();
        try {
            itemsManager.createItem(request);
            response.setCallSucceeded(true);
        } catch (InternalException e) {
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setMessage("Failed to create your item, please try again..");
        } finally {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
    }

    @RequestMapping(path = "/items/active", method = RequestMethod.POST)
    public ResponseEntity<?> getItems() {
        ItemsResponse response = new ItemsResponse();
        try {
            response.setItems(itemsManager.getAllActiveItems());
            response.setCallSucceeded(true);
        } catch (Exception e) {
            response.setMessage("Failed to fetch the items for you, please try again..");
        } finally {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
    }
}
