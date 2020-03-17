package com.entrue.pandamic.respository.elasticsearch;

import com.entrue.pandamic.model.elasticsearch.StockEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticSearchRepository extends ElasticsearchRepository<StockEntity, String> {

}
