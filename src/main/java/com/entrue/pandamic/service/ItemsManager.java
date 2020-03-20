package com.entrue.pandamic.service;

import com.entrue.pandamic.dao.ItemService;
import com.entrue.pandamic.exception.InternalException;
import com.entrue.pandamic.model.Item;
import com.entrue.pandamic.view.request.item.CreateItemRequest;
import com.entrue.pandamic.view.response.item.ItemsView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ItemsManager {

    private static final Logger logger = LogManager.getLogger(ItemsManager.class);
    @Autowired
    private ItemService itemService;

    /**
     * @param createItem
     * @throws InternalException
     */
    @Transactional
    public void createItem(@NotNull CreateItemRequest createItem) throws InternalException {
        logger.info("Creating an item={}", createItem);
        if (createItem == null) {
            logger.error("Invalid create request object received");
            throw new InternalException("Create item request has invalid request object");
        }

        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setActive(true);
        item.setItemName(createItem.getItemName());
        item.setDescription(createItem.getItemDescription());

        itemService.getItemsRepository().save(item);
        logger.info("Created item successfully item={}", item);
    }

    /**
     * @return
     */
    public List<ItemsView> getAllActiveItems() {
        logger.info("Fetching all items from repo");
        Iterable<Item> items = itemService.getItemsRepository().findAll();
        List<ItemsView> itemsView = new ArrayList<>();
        items.forEach(item -> {
           itemsView.add(new ItemsView(item));
        });

        logger.debug("Number of items found={}", itemsView.size());
        return itemsView;
    }

}
