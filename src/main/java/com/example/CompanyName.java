package com.example;

import java.util.*;

public enum CompanyName {
    GIRAFFE, DISCORD, PINK, COFFEE, HIPPO_TECH, RED;

    private static Optional<Player> aBoss = Optional.empty();

    public TotalShares getTotalShares() {
        return TotalShares.values()[this.ordinal()];
    }

    public boolean hasBoss() {
        return aBoss.isPresent();
    }

    public void setBoss(Player pPlayer) {
        aBoss = Optional.of(pPlayer);
    }

    public Player getBoss() {
        return aBoss.get();
    }
}
