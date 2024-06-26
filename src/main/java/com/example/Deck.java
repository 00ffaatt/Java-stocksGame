package com.example;

import java.util.*;

public class Deck implements Iterable<Stock> {
    private List<Stock> aStocks;

    public Deck() {
        // initialize
        aStocks = new ArrayList<Stock>();
        // generate all stocks acoordinate to how many we need
        for (CompanyName name : CompanyName.values()) {
            for (int i = 0; i < name.getTotalShares().getNumberShares(); i++) {
                aStocks.add(new Stock(name));
            }
        }
        // shuffle()
        Collections.shuffle(aStocks);
        // remove 5 stocks from top of deck
        for (int i = 0; i < 5; i++) {
            Stock removed = aStocks.remove(0);
            removed.setStatus(StockStatus.EXCLUDED);
        }
    }

    public Stock draw() {
        assert aStocks.size() > 0;
        Stock removed = aStocks.remove(0);
        removed.setStatus(StockStatus.IN_HAND);
        return removed;
    }

    public boolean isEmpty() {
        return aStocks.size() == 0;
    }

    public Iterator<Stock> iterator() {
        return aStocks.iterator();
    }

    public int size() {
        return aStocks.size();
    }

    public List<Stock> getStocks() {
        return aStocks;
    }
}
