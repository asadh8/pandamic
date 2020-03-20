package com.entrue.pandamic.respository;

import com.entrue.pandamic.model.geo.GeoLocation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface GeoLocationRepository extends CrudRepository<GeoLocation, String> {

    @Query(value="select id from GeoLocation where id in (:ids)")
    Set<String> getIdsOfExistingDataForIds(@Param("ids") Collection<String> ids);
}
