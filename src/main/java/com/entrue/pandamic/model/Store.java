package com.entrue.pandamic.model;

import javax.persistence.*;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long storeId;

    @Column(name = "name")
    private String storeName;

    @Column(name = "location_id")
    private String locationId;

    public Store() {

    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", locationId='" + locationId + '\'' +
                '}';
    }
}
