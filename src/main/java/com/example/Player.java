package com.example;

import java.util.*;

public class Player {
    private String aName;
    private Hand aHand;
    private int aNumberOfCoins;
    private int aFinalMoney;
    private boolean hasAntiMonopoly;
    private List<Stock> aPublicShares;

    public Player(Deck pDeck, String pName) {
        assert pDeck != null && pName != null;
        aHand = new Hand(pDeck);
        aNumberOfCoins = 10;
        aFinalMoney = 0;
        aName = pName;
        hasAntiMonopoly = false;
        aPublicShares = new ArrayList<Stock>();
    }

    // adds stocks in hand to public shares
    // at the end of the game
    public void revealHand() {
        aPublicShares.addAll(aHand.revealHand());
        /*
         * for (StockPile stockpile : aPublicShares) {
         * for (Stock stock : aHand.revealHand()) {
         * if (stock.getName() == stockpile.getName()) {
         * stockpile.add(stock);
         * }
         * }
         * }
         */
    }

    public List<Stock> publicShares() {
        return aPublicShares;
    }

    public void addToPublic(Stock pStock) {
        assert pStock != null;
        aPublicShares.add(pStock);
    }

    public int getNumberShares(CompanyName pCompanyName) {
        assert pCompanyName != null;
        int number = 0;
        for (Stock stock : aPublicShares) {
            if (stock.getCompanyName() == pCompanyName) {
                number++;
            }
        }
        return number;
    }

    public Hand getHand() {
        return aHand;
    }

    public String getName() {
        return aName;
    }

    public void addMoney(int pMoney) {
        assert pMoney > 0;
        aNumberOfCoins += pMoney;
    }

    public int getMoney() {
        return aNumberOfCoins;
    }

    public void discard(Stock pStock) {
        assert pStock != null;
        aHand.discard(pStock);
    }

    public void addFinalMoney(int pMoney) {
        assert pMoney > 0;
        aFinalMoney += pMoney;
    }

    public boolean hasAntiMonopoly() {
        return hasAntiMonopoly;
    }

}
