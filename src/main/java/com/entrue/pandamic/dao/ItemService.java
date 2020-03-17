package com.entrue.pandamic.dao;

import com.entrue.pandamic.model.Item;
import com.entrue.pandamic.model.Store;
import com.entrue.pandamic.respository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemsRepository itemsRepository;

    public void createItem(Item item) {
        itemsRepository.save(item);
    }

    public Iterable<Item> getItem(String itemId) {
        return itemsRepository.findAllById(Collections.singleton(itemId));
    }

    public ItemsRepository getItemsRepository() {
        return itemsRepository;
    }
}
