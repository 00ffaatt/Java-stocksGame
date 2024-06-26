package com.example;

public class MarketPresentDraw implements MarketPresentStrategy {
    public void newStockToHand(MarketPresentStrategy pStrategy, Market pMarket, Player pPlayer, Deck pDeck,
            Stock wantedStock) {
        assert pStrategy != null && pMarket.size() <= pPlayer.getMoney();
        for (Stock stock : pMarket.getStocks()) {
            stock.addCoin();
        }
        pPlayer.getHand().add(pDeck.draw());
    }
}
