package com.kosign.redoapi.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtils {

    private static final String VA_PATTERN_YYYYMM = "yyyyMM";
    private static final String VA_PATTERN_TIME6 = "HHmmss";
    private static final String VA_PATTERN_MONTH6 = "yyyyMM";
    private static final String VA_PATTERN_DATE8 = "yyyyMMdd";
    private static final String VA_PATTERN_DATE10 = "yyyy-MM-dd";
    private static final String VA_PATTERN_DTM14 = "yyyyMMddHHmmss";
    private static final String VA_PATTERN_DTM_MINUTE = "yyyyMMddHHmm";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }
    public static String formatDateTime(LocalDateTime dateTime) {
        return formatter.format(dateTime);
    }
    public static final DateTimeFormatter VA_FORMATTER_TIME6 = DateTimeFormatter.ofPattern(VA_PATTERN_TIME6);
    public static final DateTimeFormatter VA_FORMATTER_DATE8 = DateTimeFormatter.ofPattern(VA_PATTERN_DATE8);
    public static final DateTimeFormatter VA_FORMATTER_DTM14 = DateTimeFormatter.ofPattern(VA_PATTERN_DTM14);
    public static final DateTimeFormatter VA_FORMATTER_DTM10 = DateTimeFormatter.ofPattern(VA_PATTERN_DATE10);
    public static final DateTimeFormatter VA_FORMATTER_MONTH6 = DateTimeFormatter.ofPattern(VA_PATTERN_MONTH6);
    public static final DateTimeFormatter VA_FORMATTER_YYYYMM = DateTimeFormatter.ofPattern(VA_PATTERN_YYYYMM);

    public static final DateTimeFormatter VA_FORMATTER_DTM_MINUTE = DateTimeFormatter.ofPattern(VA_PATTERN_DTM_MINUTE);

    public static final Clock clock = Clock.system(TimeZone.getDefault().toZoneId());
    public static final Duration clockSkew = Duration.ofSeconds(60);

    public static LocalDateTime ictNow() {
        return LocalDateTime.now(clock);
    }

    public static String getDateNow() {
        return ictNow().format(VA_FORMATTER_DATE8);
    }

    public static String getTimeNow() {
        return ictNow().format(VA_FORMATTER_TIME6);
    }

    public static String getDateTimeNow() {
        return ictNow().format(VA_FORMATTER_DTM14);
    }

    public static LocalDateTime atEndOfDay() {
        return ictNow().toLocalDate().atTime(LocalTime.MAX);
    }

    public static boolean isToday(LocalDate date){
        return date.equals(LocalDate.now());
    }

    public static boolean isPastDate(LocalDate date){
        return date.isBefore(LocalDate.now());
    }

    public static boolean isFutureDate(LocalDate date){
        return date.isAfter(LocalDate.now());
    }

    public static LocalDateTime prevNow() {
        var localDateTime = ictNow();
        return localDateTime.minusDays(1);
    }

    public static String getDueDateTime(Long term) {
        return ictNow().plusDays(term).format(VA_FORMATTER_DTM14);
    }

    public static LocalDateTime parseDateTime(@NonNull String dateTime) {
        return LocalDateTime.parse(dateTime, VA_FORMATTER_DTM14);
    }

    public static LocalDateTime formatDateTimeMinute(@NonNull String dateTime) {
        return LocalDateTime.parse(dateTime, VA_FORMATTER_DTM_MINUTE);
    }

    public static String parseDateToString8(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        return LocalDate.parse(date).format(VA_FORMATTER_DATE8);
    }

    public static String parseTimeToString6(String time) {
        if (StringUtils.isBlank(time)) {
            return null;
        }
        return LocalTime.parse(time).format(VA_FORMATTER_TIME6);
    }

    public static String parseDate8to10(String dateTime) {
        if (StringUtils.isBlank(dateTime)) {
            return null;
        }
        return LocalDate.parse(dateTime.substring(0, 8),VA_FORMATTER_DATE8).format(VA_FORMATTER_DTM10);
    }

    public static LocalDate parseDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        return LocalDate.parse(date, VA_FORMATTER_DATE8);
    }

    public static boolean isWeekend(LocalDate date) {
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }

//    public static void main(String[] args) {
//        System.err.println(LocalDate.parse("2023-09-21").format(VA_FORMATTER_DATE8));
//        System.err.println(parseDate("2023-09-21").format(VA_FORMATTER_DATE8));
//        System.err.println(LocalTime.parse("10:10:10").format(VA_FORMATTER_TIME6));
//        System.err.println(parseDateTime("20230227125500").isAfter(LocalDateTime.now()));
//        System.err.println(DateTimeUtils.ictNow().format(VA_FORMATTER_YYYYMM).equals(parseDateTime("20220627125500").format(VA_FORMATTER_YYYYMM)));
//
//    }
//public static void main(String[] args) {
//        //M1-24-XYZZZ-00500000004 get last 8 digit and add 1
//    var last8 = "M1-24-XYZZZ-00500000004".substring("M1-24-XYZZZ-00500000004".length() - 8);
//
//    var seq = Long.parseLong("M1-24-XYZZZ-00500000004".split("-")[3]);
//    var t =  StringUtils.leftPad(seq + 1 + "", 7);
//    System.err.println(t);
//
//}

    public static String stringToDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDate.parse(value, formatter).format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
    }

    public static String stringToDateWithFormat(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDate.parse(value, formatter).format(DateTimeFormatter.ofPattern("dd, MMMM yyyy"));
    }

    public static String stringToDateYYYY_MM_DD(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDate.parse(value, formatter).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }

    public static String stringToDateYYYYMMDD(String value) {

        if(StringUtils.isBlank(value)){
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDate.parse(value, formatter).format(DateTimeFormatter.ofPattern(VA_PATTERN_DATE8));
    }

    public static String ddMMyyyyToDateYYYY_MM_dd(String value) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat(   "yyyy-MM-dd");
        Date date = inputFormat.parse(value);

        return outputFormat.format(date);
    }
    public static String formatTimeToHHmm(String time) {
        LocalTime localTime = LocalTime.parse(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localTime.format(formatter);
    }
}

