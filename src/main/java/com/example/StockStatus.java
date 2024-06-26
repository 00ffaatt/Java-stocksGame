package com.example;

public enum StockStatus {
    /*
     * Excluded means part of the 5 stock cards removed at the beginning of the game
     * by random.
     */
    IN_HAND, MARKET, PUBLIC_SHARE, EXCLUDED, IN_DECK;

    public boolean inPlay() {
        return this != EXCLUDED;
    }
}
