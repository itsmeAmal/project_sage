/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.core;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author 4m4l
 */
public class Validations {

    public static int getIntFromString(String value) {
        return Integer.parseInt(value);
    }

    public static int getIntOrZeroFromString(String value) {
        if (isInteger(value)) {
            return Integer.parseInt(value);
        } else {
            return 0;
        }
    }

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static BigDecimal getBigDecimalFromString(String value) {
        return new BigDecimal(value);
    }

    public static boolean isBigDecimal(String value) {
        try {
            new BigDecimal(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static BigDecimal getBigDecimalOrZeroFromString(String value) {
        if (isBigDecimal(value)) {
            return new BigDecimal(value);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public static Date getSqlDateByUtilDate(java.util.Date date) {
        return new Date(date.getTime());
    }

    public static Date getSqlDateByString(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return new Date(format.parse(date).getTime());
    }

    public static boolean isNotEmpty(String value) {
        return !value.isEmpty();
    }

    public static boolean isLong(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String formatWithTwoDigits(String value) {
        NumberFormat NF = NumberFormat.getInstance();
        NF.setGroupingUsed(false);
        NF.setMinimumFractionDigits(2);
        NF.setMaximumFractionDigits(2);
        return NF.format(Double.parseDouble(value));
    }

}
