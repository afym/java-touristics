package com.personal.date;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DateValues {
    public static Integer getTypes(String type) {
        HashMap<String, Integer> types = new HashMap<>();
        types.put(Types.SECONDS, Calendar.SECOND);
        types.put(Types.MINUTE, Calendar.MINUTE);
        types.put(Types.HOUR, Calendar.HOUR);
        types.put(Types.DAY, Calendar.DATE);
        types.put(Types.MONTH, Calendar.MONTH);
        types.put(Types.YEAR, Calendar.YEAR);

        return types.get(type);
    }

    public static String getFormat(String format) {
        Map<String, String> formats = new HashMap<>();

        formats.put(Formats.Y_M_D_SCORE, "yyyy-MM-dd");
        formats.put(Formats.M_D_Y_SCORE, "MM-dd-yyyy");
        formats.put(Formats.D_M_Y_SCORE, "dd-MM-yyyy");
        formats.put(Formats.Y_M_D_SLASH, "yyyy/MM/dd");
        formats.put(Formats.M_D_Y_SLASH, "MM/dd/yyyy");
        formats.put(Formats.D_M_Y_SLASH, "dd/MM/yyyy");
        formats.put(Formats.Y_M_D_CLEAR, "yyyyMMdd");
        formats.put(Formats.M_D_Y_CLEAR, "MMddyyyy");
        formats.put(Formats.D_M_Y_CLEAR, "ddMMyyyy");
        formats.put(Formats.Y_M_D_SCORE_H_I_S_COLON, "yyyy-MM-dd HH:mm:ss");
        formats.put(Formats.M_D_Y_SCORE_H_I_S_COLON, "MM-dd-yyyy HH:mm:ss");
        formats.put(Formats.D_M_Y_SCORE_H_I_S_COLON, "dd-MM-yyyy HH:mm:ss");
        formats.put(Formats.Y_M_D_SLASH_H_I_S_COLON, "yyyy/MM/dd HH:mm:ss");
        formats.put(Formats.M_D_Y_SLASH_H_I_S_COLON, "MM/dd/yyyy HH:mm:ss");
        formats.put(Formats.D_M_Y_SLASH_H_I_S_COLON, "dd/MM/yyyy HH:mm:ss");
        formats.put(Formats.Y_M_D_CLEAR_H_I_S_COLON, "yyyyMMddHHmmss");
        formats.put(Formats.M_D_Y_CLEAR_H_I_S_COLON, "MMddyyyyHHmmss");
        formats.put(Formats.D_M_Y_CLEAR_H_I_S_COLON, "ddMMyyyyHHmmss");

        return formats.get(format);
    }
}
