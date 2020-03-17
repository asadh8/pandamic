package com.entrue.pandamic.view.request.item;

/**
 * @author asadhsheriff
 */
public class CreateItemRequest {

    private String itemName;

    private String itemDescription;

    public CreateItemRequest() {

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return "CreateItemRequest{" +
                "itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                '}';
    }
}
