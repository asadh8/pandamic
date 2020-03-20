package com.entrue.pandamic.view.request.stock;

import java.io.Serializable;

/**
 * @author asadhsheriff
 */
public class BrowserLocation implements Serializable {

    private Double latitude;

    private Double longitude;

    public BrowserLocation() {

    }

    public BrowserLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
