package com.entrue.pandamic.model.geo;

import javax.persistence.*;

@Entity
@Table(schema = "pandamic", name = "locations")
public class GeoLocation {

    @Id
    @Column(name = "id")
    private String locationId;

    private double lat;

    private double lon;

    private String address1;

    private String address2;

    private String city;

    @Column(name = "state_code")
    private String stateCode;

    private String country;

    private String zipcode;

    public GeoLocation() {

    }

    public GeoLocation(BrowserLocation location) {
        setLat(location.getLatitude());
        setLon(location.getLongitude());
    }

    public GeoLocation(CreateStocksRequest req) {
        setLon(req.getLocation().getLongitude());
        setLat(req.getLocation().getLatitude());
        setAddress1(req.getAddressLine1());
        setAddress2(req.getAddressLine2());
        setCity(req.getCity());
        setStateCode(State.valueOf(req.getState().toUpperCase()).getAbbreviation());
        setCountry(req.getCountry());
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
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
