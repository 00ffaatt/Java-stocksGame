package com.example;

public class DiscardToMarket implements DiscardStrategy {
    public void discard(Player pPlayer, Stock pStock, Market pMarket) {
        assert pPlayer != null;
        pPlayer.discard(pStock);
        pMarket.addStock(pStock);
    }
}
