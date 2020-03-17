package com.entrue.pandamic.respository;

import com.entrue.pandamic.model.StoreStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends CrudRepository<StoreStock, Long> {

}
