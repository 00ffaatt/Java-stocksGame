package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StockTest {
    private final static Stock stock = new Stock(CompanyName.GIRAFFE);

    @Test
    public void testConstructorName() {
        assertEquals(CompanyName.GIRAFFE, stock.getCompanyName());
    }

    @Test
    public void testConstructorShares() {
        assertEquals(5, stock.getCompanyName().getTotalShares().getNumberShares());
    }

}
