package com.entrue.pandamic.model.geo;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class GeoLocation {

    @Id
    @Column(name = "id")
    private String locationId;

    private double lat;

    private double lon;

    public GeoLocation() {

    }

    public String getLocationId() {
        if (locationId == null || locationId == "") {
            locationId = generateId();
        }
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     *
     * @return
     */
    public String generateId() {
        String id = "".concat(String.valueOf(getLat()));
        id.concat(String.valueOf(getLon()));
        return id;
    }

    @Override
    public String toString() {
        return "GeoLocation{" +
                "locationId=" + locationId +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
