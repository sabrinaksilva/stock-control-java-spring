package com.kappann.stockcontrol.helpers;

import java.math.BigDecimal;

public class NumberTestsHelper {
    public static BigDecimal getRandomPositive () {
        BigDecimal randomNumber = BigDecimal.valueOf(Math.random());
        return randomNumber.compareTo(BigDecimal.ZERO) <= 0 ? randomNumber.negate() : randomNumber;
    }

}
