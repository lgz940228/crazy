package com.lgz.crazy.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by lgz on 2019/2/21.
 */
public class DateUtil {

    private static final String DATE_TIME_PATTERN="yyyy-MM-dd HH:mm:ss";

    public static String nowDateTime(){
        return DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(LocalDateTime.now());
    }

    public static void main(String[] args) {
        String format = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(LocalDateTime.now());
        System.out.println(format);
    }
}
