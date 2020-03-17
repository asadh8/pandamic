package com.entrue.pandamic.view.response.stock;

import com.entrue.pandamic.view.response.GenericResponse;

import java.util.List;

/**
 * @author asadhsheriff
 */
public class CreateStockResponse extends GenericResponse {

    private List<StocksView> stocks;

    public CreateStockResponse() {

    }

    public List<StocksView> getStocks() {
        return stocks;
    }

    public void setStocks(List<StocksView> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "CreateStockResponse{" +
                "stocks=" + stocks +
                '}';
    }
}
