package com.example;

import java.util.*;

/*
 * represents a pile of shares of a stock
 * also contains the number of a given stock
 */
public class StockPile {
    private Stock aStock;
    private int aNumberOfShares;

    public StockPile(Stock pStock, int pNumberOfShares) {
        assert pStock != null && pNumberOfShares >= 0;
        aStock = pStock;
        aNumberOfShares = pNumberOfShares;
    }

    public void add(Stock pStock) {
        assert pStock != null && pStock == aStock;
        aNumberOfShares++;
    }

    public String getName() {
        return aStock.getName();
    }

}
