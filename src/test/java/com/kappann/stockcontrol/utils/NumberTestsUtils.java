package com.kappann.stockcontrol.utils;

import java.math.BigDecimal;

public class NumberTestsUtils {
    public static BigDecimal getRandomPositive () {
        BigDecimal randomNumber = BigDecimal.valueOf(Math.random());
        return randomNumber.compareTo(BigDecimal.ZERO) <= 0 ? randomNumber.negate() : randomNumber;
    }

}
