package com.entrue.pandamic.controller;

import com.entrue.pandamic.exception.InternalException;
import com.entrue.pandamic.exception.StockCreationException;
import com.entrue.pandamic.service.StoreStockManagerService;
import com.entrue.pandamic.view.request.stock.BrowserLocation;
import com.entrue.pandamic.view.request.stock.CreateStock;
import com.entrue.pandamic.view.response.stock.CreateStockResponse;
import com.entrue.pandamic.view.response.stock.StocksResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StocksController {

    private static final Logger logger = LogManager.getLogger(StocksController.class);

    @Autowired
    private StoreStockManagerService storeStockManagerService;

    /**
     * @param location
     * @return
     */
    @RequestMapping(path = "/stocks", method = RequestMethod.POST)
    public ResponseEntity<?> getAvailableStock(@RequestBody BrowserLocation location) {
        StocksResponse response = new StocksResponse();
        try {
            response.setStocksViews(storeStockManagerService.getLocationBasedStocks(location));
        } catch (Exception e) {
            logger.error("Exception occurred while getting the stocks with error={}", e);
            response.setMessage("Something went wrong while fetching your stocks for your location. Please try again..");
        } finally {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
    }

    @RequestMapping(path = "/stocks/create", method = RequestMethod.POST)
    public ResponseEntity<?> introduceNewStock(@RequestBody CreateStock stock) {
        CreateStockResponse response = new CreateStockResponse();
        try {
            storeStockManagerService.createStock(stock);
            response.setStocks(storeStockManagerService.getLocationBasedStocks(stock.getLocation()));
            response.setCallSucceeded(true);
        } catch (StockCreationException e) {
            response.setMessage(e.getMessage());
        } catch (InternalException e) {
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("Exception occured while creating the stock with error={}", e);
            response.setMessage("Something went wrong while publishing your stock. Please try again..");
        } finally {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
    }

    @RequestMapping(path = "/stocks/test", method = RequestMethod.GET)
    public ResponseEntity<?> test() {
        storeStockManagerService.testQuery();
        return new ResponseEntity<>("Tested", HttpStatus.ACCEPTED);
    }
}
