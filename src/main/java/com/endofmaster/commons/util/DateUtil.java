package com.endofmaster.commons.util;

import org.apache.commons.lang3.time.DateUtils;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author ZM.Wang
 */
public class DateUtil {

    /**
     * 从一个时间区间获取中间所有的日期
     * @param start 开始
     * @param end   结束
     * @return date的list
     */
    public static List<Date> getDatesOfDateRange(Date start, Date end) {
        LocalDate localDate1 = date2LocalDate(start);
        LocalDate localDate2 = date2LocalDate(end);
        int difference = localDate2.compareTo(localDate1);
        System.err.println(difference);
        if (difference == 0) {
            return Collections.singletonList(start);
        }
        List<Date> dates = new ArrayList<>();
        for (int i = 0; i <= difference; i++) {
            dates.add(localDate2Date(localDate1.plusDays(i)));
        }
        return dates;
    }

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

    public static Date localDate2Date(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
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
