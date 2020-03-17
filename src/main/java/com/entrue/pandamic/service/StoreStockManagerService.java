package com.entrue.pandamic.service;

import com.entrue.pandamic.dao.GeoLocationService;
import com.entrue.pandamic.dao.ItemService;
import com.entrue.pandamic.dao.StockManagingService;
import com.entrue.pandamic.exception.InternalException;
import com.entrue.pandamic.exception.StockCreationException;
import com.entrue.pandamic.model.Item;
import com.entrue.pandamic.model.StoreStock;
import com.entrue.pandamic.model.geo.GeoLocation;
import com.entrue.pandamic.service.elasticsearch.StocksSearchService;
import com.entrue.pandamic.view.request.stock.BrowserLocation;
import com.entrue.pandamic.view.request.stock.CreateStock;
import com.entrue.pandamic.view.response.stock.StocksView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("storeStockManagerService")
public class StoreStockManagerService {

    private static final Logger logger = LogManager.getLogger(StoreStockManagerService.class);

    private ItemService itemService;

    private StockManagingService stockManagingService;

    private GeoLocationService geoLocationService;

    private StocksSearchService stocksSearchService;

    @Autowired
    public StoreStockManagerService(ItemService itemService, StockManagingService stockManagingService,
                                    GeoLocationService geoLocationService,
                                    StocksSearchService stocksSearchService) {
        this.itemService = itemService;
        this.stockManagingService = stockManagingService;
        this.geoLocationService = geoLocationService;
        this.stocksSearchService = stocksSearchService;
    }

    @Transactional
    public boolean createStock(@NotNull CreateStock stock) throws StockCreationException, InternalException {
        logger.info("Creating stock: {}", stock);
        try {
            if (stock == null) {
                logger.error("Invalid stock object received: {}", stock);
                throw new StockCreationException("Invalid stock object received");
            }

            // business layer stock object
            StoreStock newStock = new StoreStock(System.currentTimeMillis());

            // geo graphic location
            GeoLocation location = new GeoLocation();
            location.setLat(stock.getLocation().getLatitude());
            location.setLon(stock.getLocation().getLongitude());
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

            Optional<GeoLocation> optionalGeoLocation = geoLocationService.getGeoLocationRepository().findById(location.getLocationId());
            if (!optionalGeoLocation.isPresent()) {
                geoLocationService.getGeoLocationRepository().save(location);
            }
            stockManagingService.getStocksRepository().save(newStock);
            stocksSearchService.createStock(newStock, location);
            logger.info("Saved stock to our repository");
        } catch (Exception e) {
            logger.error("Exception occurred while processing the request to save stock with error={}", e);
            throw new StockCreationException("Failed to create stock for you. Please try again..");
        }
        return true;
    }

    public List<StocksView> getLocationBasedStocks(@NotNull BrowserLocation location) {
        return null;
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
