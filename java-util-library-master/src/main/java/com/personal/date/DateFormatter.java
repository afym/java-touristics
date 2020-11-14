package com.personal.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Formatter operations related to date.
 */
public class DateFormatter {

    /**
     * Change de format from string to string
     *
     * @param currentFormat The current date format like "Y-m-d" {@link Formats}
     * @param newFormat The new format to convert like "Ymd" {@link Formats}
     * @param date The string date format like "2017-05-01" {@link Formats}
     *
     */
    public static String fromString(String currentFormat, String newFormat, String date) {
        SimpleDateFormat oldFormat = new SimpleDateFormat(DateValues.getFormat(currentFormat));
        SimpleDateFormat changeFormat = new SimpleDateFormat(DateValues.getFormat(newFormat));

        try {
            return changeFormat.format(oldFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Change de format from string to java.util.Date
     *
     * @param format The current date format like "Y-m-d" {@link Formats}
     * @param date The string date format like "2017-05-01" {@link Formats}
     *
     */
    public static Date fromStringToDate(String format, String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DateValues.getFormat(format));

        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
