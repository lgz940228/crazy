package com.lgz.crazy.common.utils;

import java.math.BigDecimal;

/**
 * Created by lgz on 2019/2/21.
 */
public class NumUtil {

    public static boolean gt(Number number1, Number number2) {
        if (number1 == null || number2 == null) {
            return false;
        }
        if (number1 instanceof Double || number2 instanceof Double) {
            return number1.doubleValue() > number2.doubleValue();
        } else {
            return number1.longValue() > number2.longValue();
        }
    }

    public static boolean ge(Number number1, Number number2) {
        if (number1 == null || number2 == null) {
            return false;
        }
        if (number1 instanceof Double || number2 instanceof Double) {
            return number1.doubleValue() >= number2.doubleValue();
        } else {
            return number1.longValue() >= number2.longValue();
        }
    }

    /**
     * 判断数值o1 和数值o2是否相等
     * @param o1
     * @param o2
     * @return
     */
    public static boolean eq(Number o1, Number o2) {
        if (o1 == o2) {
            return true;
        } else if (o1 == null || o2 == null) {
            return false;
        } else {
            if (o1.equals(o2)) {
                return true;
            } else if (o1 instanceof Double || o1 instanceof Float || o1 instanceof BigDecimal
                    || o2 instanceof Double || o2 instanceof Float || o2 instanceof BigDecimal) {
                return o1.doubleValue() == o2.doubleValue();
            } else {
                return o1.longValue() == o2.longValue();
            }
        }
    }

    public static boolean neq(Number o1, Number o2) {
        return !eq(o1, o2);
    }

    public static boolean lt(Number number1, Number number2) {
        if (number1 == null || number2 == null) {
            return false;
        }
        if (number1 instanceof Double || number2 instanceof Double) {
            return number1.doubleValue() < number2.doubleValue();
        } else {
            return number1.longValue() < number2.longValue();
        }
    }

    public static boolean le(Number number1, Number number2) {
        if (number1 == null || number2 == null) {
            return false;
        }
        if (number1 instanceof Double || number2 instanceof Double) {
            return number1.doubleValue() <= number2.doubleValue();
        } else {
            return number1.longValue() <= number2.longValue();
        }
    }
}
