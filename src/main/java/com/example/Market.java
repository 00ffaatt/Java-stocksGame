package com.example;

import java.util.*;

public class Market implements Iterable<Stock> {
    private List<Stock> aMarketStocks;

    public Market() {
        aMarketStocks = new ArrayList<Stock>();
    }

    public void addStock(Stock pStock) {
        assert pStock != null;
        aMarketStocks.add(pStock);
    }

    public void removeStock(Stock pStock) {
        assert pStock != null && aMarketStocks.contains(pStock);
    }

    public Iterator<Stock> iterator() {
        return aMarketStocks.iterator();
    }

    public boolean isEmpty() {
        return aMarketStocks.isEmpty();
    }

    public int size() {
        return aMarketStocks.size();
    }

    public List<Stock> getStocks() {
        return Collections.unmodifiableList(aMarketStocks);
    }

    public boolean contains(Stock pStock) {
        assert pStock != null;
        return aMarketStocks.contains(pStock);
    }

}
