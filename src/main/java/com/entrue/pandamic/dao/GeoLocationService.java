package com.entrue.pandamic.dao;

import com.entrue.pandamic.respository.GeoLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationService {

    @Autowired
    private GeoLocationRepository geoLocationRepository;

    public GeoLocationRepository getGeoLocationRepository() {
        return geoLocationRepository;
    }
}
