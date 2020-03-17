package com.entrue.pandamic.view.request.stock;

import java.io.Serializable;

/**
 * @author asadhsheriff
 */
public class BrowserLocation implements Serializable {

    private Long latitude;

    private Long longitude;

    public BrowserLocation() {

    }

    public BrowserLocation(Long latitude, Long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
}
