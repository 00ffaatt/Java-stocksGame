package com.example;

import java.util.*;

public class Hand {
    private List<Stock> aStocks = new ArrayList<Stock>(3);

    public Hand(Stock pStock1, Stock pStock2, Stock pStock3) {
        assert pStock1 != null && pStock2 != null && pStock3 != null;
        aStocks.add(pStock1);
        aStocks.add(pStock2);
        aStocks.add(pStock3);
    }

    public Hand(Deck pDeck) {
        assert pDeck != null;
        for (int i = 0; i < 3; i++) {
            aStocks.add(pDeck.draw());
        }
    }

    public void sendToMarket(Stock pStock) {
        assert pStock != null;
        if (aStocks.contains(pStock)) {
            aStocks.remove(pStock);
            pStock.setStatus(StockStatus.MARKET);
        }
    }

    public void publicize(Stock pStock) {
        assert pStock != null;
        if (aStocks.contains(pStock)) {
            aStocks.remove(pStock);
            pStock.setStatus(StockStatus.PUBLIC_SHARE);
        }
    }

    public void add(Stock pStock) {
        assert pStock != null;
        aStocks.add(pStock);
    }

    public void discard(Stock pStock) {
        assert aStocks.size() == 4;
        aStocks.remove(pStock);
    }

    public List<Stock> revealHand() {
        // returns stocks in hand
        // used at the end of the game
        return aStocks;
    }

    public String toString() {
        Object[] stocks = aStocks.toArray();
        return Arrays.toString(stocks);
    }
}
