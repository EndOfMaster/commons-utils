package com.endofmaster.commons.util;

import java.time.*;
import java.util.Date;
import java.util.Locale;

/**
 * @author ZM.Wang
 */
public class DateUtil {

    public static Date getLastDayStart() {
        return getLastDay(LocalTime.MIN);
    }

    public static Date getLastDayEnd() {
        return getLastDay(LocalTime.MAX);
    }

    public static Date getAnyDayStart(LocalDate localDate) {
        LocalDateTime anyDayStart = LocalDateTime.of(localDate, LocalTime.MIN);
        return localDateTime2Date(anyDayStart);
    }

    public static Date getAnyDayEnd(LocalDate localDate) {
        LocalDateTime anyDayEnd = LocalDateTime.of(localDate, LocalTime.MAX);
        return localDateTime2Date(anyDayEnd);
    }

    public static Date getLastDay(LocalTime localTime) {
        return getPlusDay(localTime, -1);
    }

    public static Date getTodayAngDate(LocalTime localTime) {
        return localDateTime2Date(LocalDate.now(), localTime);
    }

    public static Date getPlusDayStart(int ago) {
        LocalDate agoDay = LocalDate.now().plusDays(ago);
        LocalDateTime agoDayStart = LocalDateTime.of(agoDay, LocalTime.MIN);
        return localDateTime2Date(agoDayStart);
    }

    public static Date getPlusDayEnd(int ago) {
        LocalDate agoDay = LocalDate.now().plusDays(ago);
        LocalDateTime agoDayEnd = LocalDateTime.of(agoDay, LocalTime.MAX);
        return localDateTime2Date(agoDayEnd);
    }

    public static Date getPlusDay(LocalTime localTime, int plusDay) {
        LocalDate lastDay = LocalDate.now().plusDays(plusDay);
        LocalDateTime lastDayStart = LocalDateTime.of(lastDay, localTime);
        return localDateTime2Date(lastDayStart);
    }

    public static Date localDateTime2Date(LocalDate localDate, LocalTime localTime) {
        return localDateTime2Date(LocalDateTime.of(localDate, localTime));
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 比较日期先后
     *
     * @param date1 被比较的date
     * @param date2
     * @return date2大于date1返回true
     */
    public static boolean compareByDate(Date date1, Date date2) {
        LocalDate localDate1 = date2LocalDate(date1);
        LocalDate localDate2 = date2LocalDate(date2);
        return compareByDate(localDate1, localDate2);
    }

    public static boolean compareByDate(LocalDate date1, LocalDate date2) {
        int i = date2.compareTo(date1);
        return i > 0;
    }

    public static LocalTime date2LocalTime(Date date) {
        return date2LocalDateTime(date).toLocalTime();
    }

    public static LocalDate date2LocalDate(Date date) {
        return date2LocalDateTime(date).toLocalDate();
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

}
