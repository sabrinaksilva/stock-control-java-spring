package com.kappann.stockcontrol.utils;

import java.math.BigDecimal;
import java.util.Random;

public class NumberTestsUtils {
    public static BigDecimal generateRandomBigDecimalPositive () {
        BigDecimal randomNumber = BigDecimal.valueOf(Math.random());
        return randomNumber.compareTo(BigDecimal.ZERO) <= 0 ? randomNumber.negate() : randomNumber;
    }

    public static Long generateRandomLongPositive () {
        Long randomNumber = Long.parseLong(String.valueOf(Math.random()));
        return randomNumber.compareTo(0L) <= 0 ? (randomNumber * (-1)) : randomNumber;
    }

    public static Integer generateRandomIntegerPositive () {
        return new Random().nextInt(1, 50);
    }


}
