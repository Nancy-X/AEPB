package com.example.AEPB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AirCoinTest {

    @Test
    void should_equal_when_compare_given_two_AirCoins_amount_same() {
        AirCoin A = new AirCoin(1);
        AirCoin B = new AirCoin(1);

        Assertions.assertTrue(A.equals(B));
    }

    @Test
    void should_not_equal_when_compare_given_two_AirCoins_amount_not_same() {
        AirCoin A = new AirCoin(1);
        AirCoin B = new AirCoin(100);

        Assertions.assertFalse(A.equals(B));
    }

    @Test
    void should_throw_exception_when_compare_given_a_AirCoin_amount_less_than_zero() {
        AirCoin A = new AirCoin(AirCoin.MIN_AMOUNT - 1);
        AirCoin B = new AirCoin(100);

        Assertions.assertThrows(CannotCompareException.class, () -> A.equals(B));
    }

    @Test
    void should_throw_exception_when_compare_given_a_AirCoin_amount_more_than_1_billion() {
        AirCoin A = new AirCoin(1);
        AirCoin B = new AirCoin(AirCoin.MAX_AMOUNT + 1);

        Assertions.assertThrows(CannotCompareException.class, () -> A.equals(B));
    }

    @Test
    void should_throw_exception_when_compare_given_a_AirCoin_amount_is_null() {
        AirCoin A = new AirCoin(1);
        AirCoin B = null;

        Assertions.assertThrows(CannotCompareException.class, () -> A.equals(B));
    }

    @Test
    void should_throw_exception_when_compare_given_a_AirCoin_amount_is_null_() {
        AirCoin A = null;
        AirCoin B = new AirCoin(1);

        Assertions.assertThrows(NullPointerException.class, () -> A.equals(B));
    }

}
