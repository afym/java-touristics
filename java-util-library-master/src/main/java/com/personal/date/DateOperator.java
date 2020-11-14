package com.personal.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DateOperator {
    private static String OPERATION = "operation";
    private static String TIME = "time";

    /**
     * Adds custom quantity to date from date
     *
     * @param format The string date format
     * @param date The string date value
     * @param time A string time expression like "+ 2 hours,- 1 month,+ 3 seconds,- 5 minutes, etc"
     */
    public static String operate(String format, String date, String time) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(DateValues.getFormat(format));
        Calendar calendar = getCalendar(simpleFormat, date);
        HashMap<String, String> options = getOptions(time);
        int timeValue = DateValues.getTypes(options.get(TIME));
        int quantityValue = new Integer(options.get(OPERATION));

        calendar.add(timeValue, quantityValue);

        return simpleFormat.format(calendar.getTime());
    }

    private static HashMap<String, String> getOptions(String time) {
        HashMap<String, String> options = new HashMap<>();
        String[] parts = time.split(" ");
        options.put(OPERATION, parts[0] + parts[1]);
        options.put(TIME, parts[2]);

        return options;
    }

    private static Calendar getCalendar(SimpleDateFormat format, String date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(date));
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }
}
