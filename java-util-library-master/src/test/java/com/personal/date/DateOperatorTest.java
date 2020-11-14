package com.personal.date;

import org.junit.Test;

import static com.personal.date.Formats.*;
import static junit.framework.TestCase.assertEquals;

public class DateOperatorTest {

    @Test
    public void validatingOperationCase1() throws Exception {
        String date1 = DateOperator.operate(Y_M_D_SCORE, "2015-05-01", "+ 1 months");
        String date2 = DateOperator.operate(Y_M_D_SCORE, "2015-04-01", "- 2 years");
        String date3 = DateOperator.operate(Y_M_D_SLASH, "2015/04/01", "- 2 years");
        String date4 = DateOperator.operate(Y_M_D_CLEAR, "20150101", "- 2 years");
        String date5 = DateOperator.operate(M_D_Y_CLEAR, "01011999", "- 3 months");
        String date6 = DateOperator.operate(M_D_Y_SCORE, "04-04-2000", "+ 5 days");

        assertEquals("2015-06-01", date1);
        assertEquals("2013-04-01", date2);
        assertEquals("2013/04/01", date3);
        assertEquals("20130101", date4);
        assertEquals("10011998", date5);
        assertEquals("04-09-2000", date6);
    }

    @Test
    public void validatingOperationCase2() throws Exception {
        String date1 = DateOperator.operate(Y_M_D_SCORE_H_I_S_COLON, "2015-05-01 01:01:15", "+ 2 seconds");
        String date2 = DateOperator.operate(Y_M_D_SCORE_H_I_S_COLON, "1998-05-01 05:35:15", "+ 3 minutes");
        String date3 = DateOperator.operate(Y_M_D_SLASH_H_I_S_COLON, "1998/05/01 08:01:15", "+ 2 hours");
        String date4 = DateOperator.operate(Y_M_D_SLASH_H_I_S_COLON, "2001/04/01 07:01:15", "- 1 hours");

        assertEquals("2015-05-01 01:01:17", date1);
        assertEquals("1998-05-01 05:38:15", date2);
        assertEquals("1998/05/01 10:01:15", date3);
        assertEquals("2001/04/01 06:01:15", date4);
    }
}