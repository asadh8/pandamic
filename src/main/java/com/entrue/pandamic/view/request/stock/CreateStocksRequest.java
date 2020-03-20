package com.entrue.pandamic.view.request.stock;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
public class CreateStocksRequest implements Serializable {

    private BrowserLocation location;

    private List<CreateStock> stocks;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String zipcode;

    public BrowserLocation getLocation() {
        return location;
    }

    public void setLocation(BrowserLocation location) {
        this.location = location;
    }

    public List<CreateStock> getStocks() {
        return stocks;
    }

    public void setStocks(List<CreateStock> stocks) {
        this.stocks = stocks;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
