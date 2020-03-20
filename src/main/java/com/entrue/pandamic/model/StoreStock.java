package com.entrue.pandamic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(schema = "pandamic", name = "stocks")
public class StoreStock {

    @Id
    @Column(name = "id")
    private String stockId;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "number")
    private Integer number;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "location_id")
    private String locationId;

    @Column(name = "active")
    private boolean active;

    @Column(name = "description")
    private String description;

    public StoreStock() {

    }

    public StoreStock(long id) {
        setStockId("" + id);
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StoreStock{" +
                "stockId=" + stockId +
                ", itemId='" + itemId + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", storeId=" + storeId +
                ", locationId='" + locationId + '\'' +
                ", active=" + active +
                ", description='" + description + '\'' +
                '}';
    }
}
