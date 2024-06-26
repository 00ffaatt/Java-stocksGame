package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DeckTest {

    private static final Deck deck = new Deck();

    @Test
    public void testPrintInit() {
        for (Stock stock : deck) {
            System.out.println(stock);
        }
    }

    @Test
    public void testInitSize() {
        assertEquals(40, deck.size());
    }

    @Test
    public void testDrawSize() {
        Stock stock = deck.draw();
        assertEquals(39, deck.size());
    }

    @Test
    public void testDraw() {
        Stock stock = deck.getStocks().get(0);
        Stock drawn = deck.draw();
        assertEquals(stock, drawn);
    }
}
