package com.example;

public class MarketPresentTake implements MarketPresentStrategy {
    public void newStockToHand(MarketPresentStrategy pStrategy, Market pMarket, Player pPlayer, Deck pDeck,
            Stock wantedStock) {
        assert pMarket.contains(wantedStock) && !pPlayer.hasAntiMonopoly();
        pPlayer.getHand().add(wantedStock);
        pMarket.removeStock(wantedStock);
        pPlayer.addMoney(wantedStock.takeFromMarket());
    }
}
