package com.entrue.pandamic.view.response.stock;

import com.entrue.pandamic.model.Item;
import com.entrue.pandamic.model.Store;
import com.entrue.pandamic.model.elasticsearch.StockEntity;
import com.entrue.pandamic.model.geo.GeoLocation;
import com.entrue.pandamic.view.response.item.ItemsView;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class StocksView {

    private String stockId;

    private ItemsView item;

    private GeoLocation location;

    private Store store;

    private Integer number;

    private Date startTime;

    private Date endTime;

    public StocksView(StockEntity entity) {
        setEndTime(entity.getEndTime());
        setStartTime(entity.getStartTime());
        setNumber(entity.getNumber());
        setStockId(entity.getStockId());
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public ItemsView getItem() {
        return item;
    }

    public void setItem(ItemsView item) {
        this.item = item;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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

    @Override
    public String toString() {
        return "StocksView{" +
                "stockId=" + stockId +
                ", item=" + item +
                ", location=" + location +
                ", store=" + store +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
