package com.example;

public interface MarketPresentStrategy {
    public void newStockToHand(MarketPresentStrategy pStrategy, Market pMarket, Player pPlayer, Deck pDeck,
            Stock wantedStock);
}
