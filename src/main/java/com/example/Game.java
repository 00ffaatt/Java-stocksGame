package com.example;

import java.util.*;

public class Game {
    private List<Player> aPlayers;
    private Player currentPlayer;
    private Deck aDeck;
    private Market aMarket;
    private int aNumberOfPlayers;
    private DiscardStrategy aStrategy;

    public Game(int pNumberOfPlayers) {
        assert pNumberOfPlayers > 1;
        aNumberOfPlayers = pNumberOfPlayers;
        aPlayers = new ArrayList<Player>();
        aDeck = new Deck();
        aMarket = new Market();

        for (int i = 0; i < pNumberOfPlayers; i++) {
            // adds new player with fresh hand drawn from fresh deck
            aPlayers.add(new Player(aDeck, "TempName"));
        }
        currentPlayer = aPlayers.get(0);
    }

    public void playGame() {
        while (!gameIsOver()) {
            for (Player player : aPlayers) {
                // player performs turn
                currentPlayer = player;

            }
        }

        // assigns money to each player depending on stock shares owned
        for (Player player : aPlayers) {
            player.revealHand();
        }
        for (CompanyName companyName : CompanyName.values()) {
            int max = 0;
            for (Player player : aPlayers) {
                if (player.getNumberShares(companyName) > max) {
                    max = player.getNumberShares(companyName);
                    companyName.setBoss(player);
                }
            }
            for (Player player : aPlayers) {
                if (player.getNumberShares(companyName) == max && companyName.hasBoss()) {
                    companyName.setBoss(null);
                    break;
                }
            }
            for (Player player : aPlayers) {
                if (companyName.hasBoss()) {
                    Player boss = companyName.getBoss();
                    if (player != boss) {
                        player.addFinalMoney(player.getNumberShares(companyName));
                    }
                }
            }
        }
    }

    public void turnDraw(Player pPlayer) {
        pPlayer.getHand().add(aDeck.draw());
    }

    public void discard(Player pPlayer, DiscardStrategy pStrategy, Stock pStock) {
        discard(pStrategy, pPlayer, pStock, aMarket);
    }

    public void performTurn(Player pPlayer, DiscardStrategy pStrategy, MarketPresentStrategy pMarketStrategy,
            Stock pStock) {
        if (aMarket.isEmpty()) {
            pPlayer.getHand().add(aDeck.draw());
            discard(pStrategy, pPlayer, pStock, aMarket);
        } else {
            marketPresentGetCard(pMarketStrategy, aDeck, pStock, pPlayer, aMarket);
            discard(pStrategy, pPlayer, pStock, aMarket);
        }
    }

    public void discard(DiscardStrategy pStrategy, Player pPlayer, Stock pStock, Market pMarket) {
        assert pStrategy != null;
        pStrategy.discard(pPlayer, pStock, pMarket);
    }

    public Market getMarket() {
        return aMarket;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Player> getPlayers() {
        return aPlayers;
    }

    public int getNumberCardsLeft() {
        return aDeck.size();
    }

    public void marketPresentGetCard(MarketPresentStrategy pStrategy, Deck pDeck, Stock pStock, Player pPlayer,
            Market pMarket) {
        assert pStrategy != null;
        pStrategy.newStockToHand(pStrategy, pMarket, pPlayer, pDeck, pStock);
    }

    public boolean gameIsOver() {
        assert aDeck != null;
        return aDeck.isEmpty();
    }
}
