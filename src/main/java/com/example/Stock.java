package com.example;

public class Stock {
    private final CompanyName aCompanyName;
    private final TotalShares aTotalShares;
    private StockStatus aStatus;
    private int aNumberOfCoins;

    public Stock(CompanyName pCompanyName) {
        assert pCompanyName != null;
        aCompanyName = pCompanyName;
        aTotalShares = pCompanyName.getTotalShares();
        aStatus = StockStatus.IN_DECK;
        aNumberOfCoins = 0;
    }

    public void setStatus(StockStatus pStatus) {
        assert pStatus != null && aStatus.inPlay();
        aStatus = pStatus;
    }

    public boolean inPlay() {
        assert aStatus != null;
        return aStatus.inPlay();
    }

    public void addCoin() {
        assert aStatus == StockStatus.MARKET;
        aNumberOfCoins++;
    }

    public int takeFromMarket() {
        assert aStatus == StockStatus.MARKET;
        int temp = aNumberOfCoins;
        aNumberOfCoins = 0;
        aStatus = StockStatus.IN_HAND;
        return temp;
    }

    public String getName() {
        return aCompanyName.toString();
    }

    public CompanyName getCompanyName() {
        return aCompanyName;
    }

    public String toString() {
        return "Name: " + aCompanyName.toString() + "\n" + "Total#Shares: " + aTotalShares.toString()
                + "\n" + "Coins: " + aNumberOfCoins;
    }

}
