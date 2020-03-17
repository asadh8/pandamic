package com.entrue.pandamic.view.response.stock;

import com.entrue.pandamic.view.response.GenericResponse;

import java.util.List;

/**
 * @author asadhsheriff
 */
public class StocksResponse extends GenericResponse {

    private List<StocksView> stocksViews;

    public List<StocksView> getStocksViews() {
        return stocksViews;
    }

    public void setStocksViews(List<StocksView> stocksViews) {
        this.stocksViews = stocksViews;
    }

    @Override
    public String toString() {
        return "StocksResponse{" +
                "stocksViews=" + stocksViews +
                '}';
    }
}
