package com.entrue.pandamic.view.response.item;

import com.entrue.pandamic.view.response.GenericResponse;

import java.util.List;

public class ItemsResponse extends GenericResponse {

    private List<ItemsView> items;

    public List<ItemsView> getItems() {
        return items;
    }

    public void setItems(List<ItemsView> items) {
        this.items = items;
    }
}
