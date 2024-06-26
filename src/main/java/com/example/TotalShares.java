package com.example;

public enum TotalShares {
    FIVE, SIX, SEVEN, EIGHT, NINE, TEN;

    public int getNumberShares() {
        return this.ordinal() + 5;
    }
}
