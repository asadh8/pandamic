package com.entrue.pandamic.dao;

import com.entrue.pandamic.respository.StocksRepository;
import com.entrue.pandamic.respository.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockManagingService {

    @Autowired
    private StocksRepository stocksRepository;

    public StocksRepository getStocksRepository() {
        return this.stocksRepository;
    }
}
