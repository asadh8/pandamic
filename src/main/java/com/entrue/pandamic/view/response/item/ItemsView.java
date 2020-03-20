package com.entrue.pandamic.view.response.item;

import com.entrue.pandamic.model.Item;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class ItemsView implements Serializable {

    private String id;

    private String name;

    private String description;

    public ItemsView(Item item) {
        setName(item.getItemName());
        setDescription(item.getDescription());
        setId(item.getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ItemsView{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
