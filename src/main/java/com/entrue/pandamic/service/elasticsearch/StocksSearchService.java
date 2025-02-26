package com.entrue.pandamic.service.elasticsearch;

import com.entrue.pandamic.exception.elasticsearch.InvalidInputException;
import com.entrue.pandamic.model.StoreStock;
import com.entrue.pandamic.model.elasticsearch.StockEntity;
import com.entrue.pandamic.model.geo.GeoLocation;
import com.entrue.pandamic.model.util.Pair;
import com.entrue.pandamic.view.request.stock.BrowserLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.ResponseException;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StocksSearchService {

    private static final String ES_INDEX = "stocks";

    private static final String GEO_POINT_PARAMETER = "location";

    private static final Logger logger = LogManager.getLogger(StocksSearchService.class);

    private ElasticsearchOperations elasticsearchOperations;

    private ResultsMapper resultsMapper;

    @Autowired
    private StocksSearchService(ElasticsearchOperations elasticsearchOperations,
                                ResultsMapper resultsMapper) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.resultsMapper = resultsMapper;
    }

    /**
     * <p>
     * Method that will retrieve all the search result which are near {@link BrowserLocation}
     * </p>
     *
     * @param location
     * @return
     */
    public List<StockEntity> searchNearByStocks(BrowserLocation location) throws InvalidInputException {
        logger.info("Fetching all the stocks near by location={}", location);
        // Geo distance query.
        QueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery(GEO_POINT_PARAMETER)
                .distance(5D, DistanceUnit.MILES).geoDistance(GeoDistance.ARC)
                .point(location.getLatitude(), location.getLongitude());
        // apply sorting based on distance
        SortBuilder<?> sortBuilder = SortBuilders
                .geoDistanceSort("location", location.getLatitude(), location.getLongitude())
                .order(SortOrder.ASC).unit(DistanceUnit.MILES).geoDistance(GeoDistance.ARC);
        // generate query
        SearchQuery query = new NativeSearchQueryBuilder().withIndices(ES_INDEX).withQuery(QueryBuilders.boolQuery()
                .must(QueryBuilders.matchAllQuery())).withFilter(geoDistanceQueryBuilder).withSort(sortBuilder).build();

        try {

            return elasticsearchOperations.query(query, searchResponse -> {
                if (searchResponse.getHits().getHits().length > 0) {
                    List<StockEntity> entities = new ArrayList<>();
                    for (SearchHit hit : searchResponse.getHits().getHits()) {
                        resultsMapper.mapSearchHit(hit, StockEntity.class);
                    }
                    return entities;
                }
                return new ArrayList<>(0);
            });
        } catch (Exception e) {
            if (e instanceof ResponseException) {
                ResponseException exception = (ResponseException) e;
                String reason = exception.getResponse().getStatusLine().getReasonPhrase();
                if (reason.indexOf("latitude") > -1 || reason.indexOf("longitude") > -1) {

                    throw new InvalidInputException("Invalid location being passed, location=" + location);
                }
            }
        }
    }

    /**
     * <p>
     * Delete a stock from elastic search
     * </p>
(??)
     *
     * @param stockId
     * @return
     */
    public String deleteStock(@NotNull String stockId) {
        logger.info("Deleting a stock from Elastic Search for id={}", stockId);
        return elasticsearchOperations.delete(ES_INDEX, null, stockId);
    }

    /**
     * <p>
     * Creates a stock in the Elastic search for the location {@link GeoLocation}
     * </p>
     *
     * @param storeStockLocationPairs
     */
    public void createStocks(@NotNull List<Pair<StoreStock, GeoLocation>> storeStockLocationPairs) {
        logger.info("Bulk inserting all the documents to the index={} and no.of stocks={}", ES_INDEX, storeStockLocationPairs.size());
        List<IndexQuery> indexQueries = storeStockLocationPairs.parallelStream().map(pair -> {
            StockEntity entity = new StockEntity(pair.getFirst(), pair.getSecond());
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(entity.getStockId());
            indexQuery.setObject(entity);
            return indexQuery;
        }).collect(Collectors.toList());

        elasticsearchOperations.bulkIndex(indexQueries);
    }

}
