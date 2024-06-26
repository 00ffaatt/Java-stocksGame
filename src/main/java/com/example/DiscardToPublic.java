package com.example;

public class DiscardToPublic implements DiscardStrategy {
    public void discard(Player pPlayer, Stock pStock, Market pMarket) {
        assert pStock != null && pPlayer != null && pMarket != null;
        pPlayer.discard(pStock);
        pPlayer.addToPublic(pStock);
    }
}
