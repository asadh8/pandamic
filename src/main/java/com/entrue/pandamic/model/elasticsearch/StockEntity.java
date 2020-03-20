package com.entrue.pandamic.model.elasticsearch;

import com.entrue.pandamic.model.StoreStock;
import com.entrue.pandamic.model.geo.GeoLocation;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "stocks")
@NoArgsConstructor
public class StockEntity implements Serializable {

    private static final long serialVersionUID = -5028580216719997315L;

    @Id
    @Field(type = FieldType.Keyword)
    private String stockId;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Text)
    private String itemId;

    @Field(type = FieldType.Integer)
    private Integer number;

    @Field(type = FieldType.Date)
    private Date startTime;

    @Field(type = FieldType.Date)
    private Date endTime;

    @Field(type = FieldType.Long)
    private Long storeId;

    @Field(type = FieldType.Text)
    private String locationId;

    @Field(type = FieldType.Boolean)
    private boolean active;

    @Field(type = FieldType.Text)
    private String description;

    public StockEntity(StoreStock stock, GeoLocation geoLocation) {
        setStoreId(stock.getStoreId());
        setActive(stock.isActive());
        setDescription(stock.getDescription());
        setEndTime(stock.getEndTime());
        setItemId(stock.getItemId());
        GeoPoint geoPoint = new GeoPoint(geoLocation.getLat(), geoLocation.getLon());
        setLocation(geoPoint);
        setLocationId(stock.getLocationId());
        setNumber(stock.getNumber());
        setStartTime(stock.getStartTime());
        setStockId(stock.getStockId());
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

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "StockEntity{" +
                "stockId=" + stockId +
                ", location=" + location +
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
