package com.personal.date;

import org.junit.Test;

import java.util.Date;

import static com.personal.date.Formats.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DateFormatterTest {
    @Test
    public void validatingFormatCase1() {
        String inputDate = "2017-05-13";
        String date = DateFormatter.fromString(Y_M_D_SCORE, Y_M_D_SLASH, inputDate);
        assertEquals("2017/05/13", date);
    }

    @Test
    public void validatingFormatCase2() {
        String inputDate = "2019-04-22";
        String date = DateFormatter.fromString(Y_M_D_SCORE, D_M_Y_SCORE, inputDate);
        assertEquals("22-04-2019", date);
    }

    @Test
    public void validatingFormatCase3() {
        String inputDate = "2019-04-22";
        String date = DateFormatter.fromString(Y_M_D_SCORE, D_M_Y_CLEAR, inputDate);
        assertEquals("22042019", date);
    }

    @Test
    public void validatingFormatCase4() {
        String inputDate = "2019-04-22 23:12:05";
        String date = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, D_M_Y_SLASH, inputDate);
        assertEquals("22/04/2019", date);
    }

    @Test
    public void validatingFormatCase5() {
        String inputDate = "2017-01-21 23:12:05";
        String date = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, Y_M_D_CLEAR_H_I_S_COLON, inputDate);
        assertEquals("20170121231205", date);
    }

    @Test
    public void validateFormatToDate1() {
        String date = "2015-05-01";
        Date dateObject = DateFormatter.fromStringToDate(Y_M_D_SCORE, date);
        assertNotNull(dateObject);
        assertEquals("2015-05-01", date.toString());
    }

    @Test
    public void validateFormatToDate2() {
        String date = "2017/05/01 05:01:02";
        Date dateObject = DateFormatter.fromStringToDate(Y_M_D_SLASH_H_I_S_COLON, date);
        assertNotNull(dateObject);
        assertEquals("2017/05/01 05:01:02", date.toString());
    }

    @Test
    public void validateFormatToDateTotal() {
        String inputDate = "2017-01-21 23:12:05";

        String date1 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, Y_M_D_SCORE, inputDate);
        String date2 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, M_D_Y_SCORE, inputDate);
        String date3 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, D_M_Y_SCORE, inputDate);
        String date4 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, Y_M_D_SLASH, inputDate);
        String date5 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, M_D_Y_SLASH, inputDate);
        String date6 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, D_M_Y_SLASH, inputDate);
        String date7 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, Y_M_D_CLEAR, inputDate);
        String date8 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, M_D_Y_CLEAR, inputDate);
        String date9 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, D_M_Y_CLEAR, inputDate);
        String date10 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, Y_M_D_SCORE_H_I_S_COLON, inputDate);
        String date11 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, M_D_Y_SCORE_H_I_S_COLON, inputDate);
        String date12 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, D_M_Y_SCORE_H_I_S_COLON, inputDate);
        String date13 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, Y_M_D_SLASH_H_I_S_COLON, inputDate);
        String date14 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, M_D_Y_SLASH_H_I_S_COLON, inputDate);
        String date15 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, D_M_Y_SLASH_H_I_S_COLON, inputDate);
        String date16 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, Y_M_D_CLEAR_H_I_S_COLON, inputDate);
        String date17 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, M_D_Y_CLEAR_H_I_S_COLON, inputDate);
        String date18 = DateFormatter.fromString(Y_M_D_SCORE_H_I_S_COLON, D_M_Y_CLEAR_H_I_S_COLON, inputDate);

        assertEquals("2017-01-21", date1);
        assertEquals("01-21-2017", date2);
        assertEquals("21-01-2017", date3);
        assertEquals("2017/01/21", date4);
        assertEquals("01/21/2017", date5);
        assertEquals("21/01/2017", date6);
        assertEquals("20170121", date7);
        assertEquals("01212017", date8);
        assertEquals("21012017", date9);
        assertEquals("2017-01-21 23:12:05", date10);
        assertEquals("01-21-2017 23:12:05", date11);
        assertEquals("21-01-2017 23:12:05", date12);
        assertEquals("2017/01/21 23:12:05", date13);
        assertEquals("01/21/2017 23:12:05", date14);
        assertEquals("21/01/2017 23:12:05", date15);
        assertEquals("20170121231205", date16);
        assertEquals("01212017231205", date17);
        assertEquals("21012017231205", date18);
    }
}
