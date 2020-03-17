package com.entrue.pandamic.respository;

import com.entrue.pandamic.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends CrudRepository<Item, String> {

}
