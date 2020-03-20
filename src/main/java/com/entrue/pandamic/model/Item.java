package com.entrue.pandamic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author asadhsheriff
 */
@Entity
@Table(schema = "pandamic",name = "items")
public class Item {

    @Id
    private String id;

    @Column(name = "name")
    private String itemName;

    @Column(name = "active")
    private boolean active;

    @Column(name = "description")
    private String description;

    public Item() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", active=" + active +
                ", description='" + description + '\'' +
                '}';
    }
}
