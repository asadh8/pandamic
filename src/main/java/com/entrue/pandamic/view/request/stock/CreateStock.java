package com.entrue.pandamic.view.request.stock;

/**
 * @author asadhsheriff
 */
public class CreateStock {

    private String itemId;

    private BrowserLocation location;

    private Integer countOfItems;

    private String startTimestamp;

    private String endTimestamp;

    private String description;

    public CreateStock() {

    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public BrowserLocation getLocation() {
        return location;
    }

    public void setLocation(BrowserLocation location) {
        this.location = location;
    }

    public Integer getCountOfItems() {
        return countOfItems;
    }

    public void setCountOfItems(Integer countOfItems) {
        this.countOfItems = countOfItems;
    }

    public String getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(String startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public String getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(String endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
