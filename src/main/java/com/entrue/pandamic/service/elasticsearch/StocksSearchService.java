package com.entrue.pandamic.service.elasticsearch;

import com.entrue.pandamic.model.StoreStock;
import com.entrue.pandamic.model.elasticsearch.StockEntity;
import com.entrue.pandamic.model.geo.GeoLocation;
import com.entrue.pandamic.respository.elasticsearch.ElasticSearchRepository;
import com.entrue.pandamic.view.request.stock.BrowserLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class StocksSearchService {

    private static final String ES_INDEX = "stocks";

    private ElasticsearchOperations elasticsearchOperations;

    private ElasticSearchRepository elasticSearchRepository;

    @Autowired
    private StocksSearchService(ElasticsearchOperations elasticsearchOperations,
                                ElasticSearchRepository elasticSearchRepository) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public void searchNearByStocks(BrowserLocation location) {
        StockEntity entity = elasticsearchOperations.queryForObject(GetQuery.getById(""), StockEntity.class);
    }

    /**
     *
     * @param stock
     * @param location
     */
    public void createStock(@NotNull StoreStock stock, @NotNull GeoLocation location) {
            StockEntity entity = new StockEntity(stock, location);
            elasticSearchRepository.save(entity);
    }

}
