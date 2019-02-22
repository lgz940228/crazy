package com.lgz.crazy.common.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by lgz on 2019/2/21.
 */
public class DateUtil {

    private static final String DATE_TIME_PATTERN="yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间 格式 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String nowDateTime(){
        return DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(LocalDateTime.now());
    }

    public static String Timestamp2DateTime(Long stimestamp){
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(stimestamp);
    }

    public static long obtainTimestamp(){
        return Instant.now().toEpochMilli();
    }
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        String s = Timestamp2DateTime(l);
        System.out.println(s);

    }

}
