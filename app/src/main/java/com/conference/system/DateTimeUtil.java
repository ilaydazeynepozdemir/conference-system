package com.conference.system;


import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
    public static final Date FOUR_PM = new Time(16, 0, 0);
    public static final Date FIVE_PM = new Time(17, 0, 0);
    public static final Date TWELVE_PM = new Time(12, 0, 0);

    private DateTimeUtil() {
    }

    private static DateTimeFormatter timeFormatter;

    public static synchronized DateTimeFormatter getInstance() {
        if (timeFormatter == null) {
            timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
        }
        return timeFormatter;
    }

    public static String getTime(Date date) {
        LocalTime localTime = LocalTime.of(date.getHours(), date.getMinutes());
        return localTime.format(getInstance());
    }

    public static Date addMinutes(Date time, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.MINUTE, min);
        return calendar.getTime();
    }

    public static Date getDate(String dateStr) {
        TemporalAccessor temporalAccessor = getInstance().parse(dateStr);
        return new Date();
    }

    public static int compare(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

}
