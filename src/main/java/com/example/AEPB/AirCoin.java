package com.example.AEPB;

public class AirCoin {

    private int amount;

    public static final int MIN_AMOUNT = 0;
    public static final int MAX_AMOUNT = 1000000000;

    private static final String AMOUNT_REGULATION = "amount should be no less than "
            + MIN_AMOUNT + " and no more than " + MAX_AMOUNT;

    AirCoin(int amount) {
        this.amount = amount;
    }

    public boolean equals(AirCoin airCoin) throws CannotCompareException {
        if (this.amount < MIN_AMOUNT || this.amount > MAX_AMOUNT)
            throw new CannotCompareException(AMOUNT_REGULATION);
        if (airCoin == null || airCoin.amount < MIN_AMOUNT || airCoin.amount > MAX_AMOUNT)
            throw new CannotCompareException(AMOUNT_REGULATION);
        return airCoin.amount == this.amount;
    }
}
