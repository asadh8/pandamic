package com.entrue.pandamic.respository;

import com.entrue.pandamic.model.geo.GeoLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoLocationRepository extends CrudRepository<GeoLocation, String> {
}
