package com.entrue.pandamic.service;

import com.entrue.pandamic.dao.GeoLocationService;
import com.entrue.pandamic.dao.ItemService;
import com.entrue.pandamic.dao.StockManagingService;
import com.entrue.pandamic.dao.StoreService;
import com.entrue.pandamic.exception.InternalException;
import com.entrue.pandamic.exception.StockCreationException;
import com.entrue.pandamic.model.Item;
import com.entrue.pandamic.model.StoreStock;
import com.entrue.pandamic.model.elasticsearch.StockEntity;
import com.entrue.pandamic.model.geo.GeoLocation;
import com.entrue.pandamic.model.util.Pair;
import com.entrue.pandamic.service.elasticsearch.StocksSearchService;
import com.entrue.pandamic.view.request.stock.BrowserLocation;
import com.entrue.pandamic.view.request.stock.CreateStock;
import com.entrue.pandamic.view.request.stock.CreateStocksRequest;
import com.entrue.pandamic.view.response.item.ItemsView;
import com.entrue.pandamic.view.response.stock.StocksView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("storeStockManagerService")
public class StoreStockManagerService {

    private static final Logger logger = LogManager.getLogger(StoreStockManagerService.class);

    private ItemService itemService;

    private StockManagingService stockManagingService;

    private GeoLocationService geoLocationService;

    private StocksSearchService stocksSearchService;

    private StoreService storeService;

    @Autowired
    public StoreStockManagerService(ItemService itemService, StockManagingService stockManagingService,
                                    GeoLocationService geoLocationService,
                                    StocksSearchService stocksSearchService,
                                    StoreService storeService) {
        this.itemService = itemService;
        this.stockManagingService = stockManagingService;
        this.geoLocationService = geoLocationService;
        this.stocksSearchService = stocksSearchService;
        this.storeService = storeService;
    }

    public void testQuery() {
        stocksSearchService.searchNearByStocks(new BrowserLocation(33.7051648D, -117.74853119999999D));
        StoreStock entity = new StoreStock();
        entity.setStartTime(new Date());
        entity.setNumber(100);
        entity.setLocationId("jlkjsadasd");
        entity.setItemId("test");
        entity.setEndTime(new Date());
        entity.setDescription("kjhkjhjaskjdhajkshdakjs hkj dsahdk ajah");
        entity.setActive(true);
        entity.setStoreId(876823L);
        entity.setStockId("asadh_id");

//        try {
//            stocksSearchService.createStock(entity, new GeoLocation(33.7051648D, -117.74853119999999D));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }

    @Transactional
    public boolean createStocks(@NotNull CreateStocksRequest stocks) throws StockCreationException {
        logger.info("Creating stock: {}", stocks);
        // validate inputs
        if (stocks == null || stocks.getStocks() == null) {
            logger.error("Invalid stock object received: {}", stocks);
            throw new StockCreationException("Invalid stock object received");
        }

        try {
            List<Pair<StoreStock, GeoLocation>> stockToLocationPair = convertStocksRequestToStocks(stocks);
            saveStocks(stockToLocationPair);
            logger.info("Saved stocks to our repository");
        } catch (Exception e) {
            logger.error("Exception occurred while processing the request to save stock with error={}", e);
            throw new StockCreationException("Failed to create stock for you. Please try again..");
        }
        return true;
    }

    /**
     * @param location
     * @return
     */
    public List<StocksView> getLocationBasedStocks(@NotNull BrowserLocation location) {
        logger.info("Getting all the stocks for the location={}", location);

        StopWatch sw = new StopWatch("Get stocks based on location");
        sw.setKeepTaskList(true);

        sw.start("Get locations from elastic search");
        List<StockEntity> entities = stocksSearchService.searchNearByStocks(location);
        sw.stop();

        logger.info(sw.prettyPrint());
        return entities.stream().map(entity -> {
            StocksView stockView = new StocksView(entity);
            stockView.setItem(new ItemsView(itemService.getItemsRepository().findById(entity.getItemId()).get()));
            if (entity.getLocationId() != null && !entity.getLocationId().equals("")) {
                stockView.setLocation(geoLocationService.getGeoLocationRepository().findById(entity.getLocationId()).get());
            }
            if (entity.getStoreId() != null && !entity.getStoreId().equals("")) {
                stockView.setStore(storeService.getRepository().findById(entity.getStoreId()).get());
            }
            return stockView;
        }).collect(Collectors.toList());
    }

    /**
     * @param request
     * @return
     * @throws StockCreationException
     * @throws InternalException
     */
    private List<Pair<StoreStock, GeoLocation>> convertStocksRequestToStocks(@NotNull CreateStocksRequest request)
            throws StockCreationException, InternalException {
        List<Pair<StoreStock, GeoLocation>> stockPair = new ArrayList<>();

        for (CreateStock stock : request.getStocks()) {
            // business layer stock object
            StoreStock newStock = new StoreStock(System.currentTimeMillis());
            // geo graphic location
            GeoLocation location = new GeoLocation(request);
            newStock.setLocationId(location.getLocationId());
            // validate item
            Optional<Item> item = itemService.getItemsRepository().findById(stock.getItemId());
            if (!item.isPresent()) {
                throw new StockCreationException("Invalid item being used in the request");
            }
            // time attributes
            setTimeAttributes(stock, newStock);
            // validate store
            newStock.setDescription(stock.getDescription().substring(0, 300));
            newStock.setActive(true);
            stockPair.add(new Pair<>(newStock, location));
        }
        return stockPair;
    }

    /**
     * <p>
     * This stores all the {@link StoreStock} & {@link GeoLocation} to MySQL and ElasticSearch
     * </p>
     *
     * @param storeStockGeoLocationPair
     */
    private void saveStocks(List<Pair<StoreStock, GeoLocation>> storeStockGeoLocationPair) {
        StopWatch sw = new StopWatch("Save stocks");
        sw.setKeepTaskList(true);

        List<GeoLocation> locationsToInsert = getStocksLocationsToCreate(storeStockGeoLocationPair.stream()
                .map(Pair::getSecond).collect(Collectors.toList()));
        sw.start("Saving geo locations of size=" + locationsToInsert.size());
        geoLocationService.getGeoLocationRepository().saveAll(locationsToInsert);
        sw.stop();

        sw.start("Saving stocks of size=" + storeStockGeoLocationPair.size());
        stockManagingService.getStocksRepository().saveAll(storeStockGeoLocationPair
                .stream().map(Pair::getFirst).collect(Collectors.toList()));
        sw.stop();

        sw.start("Create stocks in elastic search");
        stocksSearchService.createStocks(storeStockGeoLocationPair);
        sw.stop();

        logger.info(sw.prettyPrint());
    }

    /**
     * <p>
     * Gets all {@link GeoLocation} that is not there in database
     * </p>
     *
     * @param locations
     * @return
     */
    private List<GeoLocation> getStocksLocationsToCreate(List<GeoLocation> locations) {
        Collection<String> ids = locations.stream().map(geoLocation ->
                geoLocation.getLocationId()).collect(Collectors.toSet());
        Set<String> existingIds = geoLocationService.getGeoLocationRepository().getIdsOfExistingDataForIds(ids);
        return locations.stream().filter(geoLocation ->
                existingIds.contains(geoLocation.getLocationId())).collect(Collectors.toList());
    }

    private void setTimeAttributes(CreateStock stock, StoreStock newStock) throws InternalException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DDTHH:mm:ss.SSS");

        if (stock.getStartTimestamp() != null) {
            try {
                newStock.setStartTime(dateFormat.parse(stock.getStartTimestamp()));
            } catch (ParseException e) {
                logger.error("Datetime format is wrong error={}", e);
                throw new InternalException();
            }
        } else {
            newStock.setStartTime(new Date());
        }

        if (stock.getEndTimestamp() == null) {
            try {

                newStock.setEndTime(dateFormat.parse(stock.getStartTimestamp()));
            } catch (ParseException e) {
                logger.error("Datetime format is wrong error={}", e);
                throw new InternalException();
            }
        } else {
            newStock.setEndTime(new Date());
        }

    }

}
