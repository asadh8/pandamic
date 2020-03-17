package com.entrue.pandamic.respository;

import com.entrue.pandamic.model.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends CrudRepository<Store, Long> {

}
