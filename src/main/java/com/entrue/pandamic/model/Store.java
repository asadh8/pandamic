package com.entrue.pandamic.model;

import com.entrue.pandamic.model.util.GeoLocation;

public class Store {

    private String storeId;

    private String storeName;

    private GeoLocation storeLocation;

    public Store() {

    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public GeoLocation getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(GeoLocation storeLocation) {
        this.storeLocation = storeLocation;
    }
}
