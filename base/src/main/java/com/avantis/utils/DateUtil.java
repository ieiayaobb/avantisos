/*
* =========================================================================
*  Copyright ?2014 NCS Pte. Ltd. All Rights Reserved
*
*  This software is confidential and proprietary to NCS Pte. Ltd. You shall
*  use this software only in accordance with the terms of the license
*  agreement you entered into with NCS.  No aspect or part or all of this
*  software may be reproduced, modified or disclosed without full and
*  direct written authorisation from NCS.
*
*  NCS SUPPLIES THIS SOFTWARE ON AN ?AS IS? BASIS. NCS MAKES NO
*  REPRESENTATIONS OR WARRANTIES, EITHER EXPRESSLY OR IMPLIEDLY, ABOUT THE
*  SUITABILITY OR NON-INFRINGEMENT OF THE SOFTWARE. NCS SHALL NOT BE LIABLE
*  FOR ANY LOSSES OR DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
*  MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*  =========================================================================
*/

package com.avantis.utils;

import com.avantis.common.exception.DateConversionException;
import org.apache.commons.collections.FastHashMap;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {


    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_M_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String TIME_FORMAT_HH_MM = "HH:mm";
    public static final String TIME_M_FORMAT = "HH:mm:ss.SSS";

    private static String DEFAULT_TIMEZONE = "Asia/Hong_Kong";


    private static ThreadLocal<FastHashMap> threadLocal = new ThreadLocal<FastHashMap>() {
        protected synchronized FastHashMap initialValue() {
            FastHashMap fastHashMap = new FastHashMap();
            fastHashMap.setFast(true);
            return fastHashMap;
        }
    };



    private static DateFormat getCachedFormat(String format) {
        DateFormat stored_format = (DateFormat)threadLocal.get().get(format);
        if(stored_format == null){
            stored_format = new SimpleDateFormat(format);
            stored_format.setLenient(false);
            threadLocal.get().put(format, stored_format);
        }
        return stored_format;
    }

    public static Date parseDate(String textDate) {
        return parseDate(textDate, false);
    }

    public static Date parseDate(String textDate, boolean throwException) {
       return parse(textDate,DATE_FORMAT,throwException);
    }

    public static Date parseDateTime(String textDate) {
        return parseDateTime(textDate, false);
    }

    public static Date parseDateTime(String textDate,boolean throwException) {
        return parse(textDate,DATETIME_FORMAT,throwException);
    }

    public static Date parseDateTimeM(String textDate) {
        return parseDateTimeM(textDate, false);
    }

    public static Date parseDateTimeM(String textDate,boolean throwException) {
        return parse(textDate,DATETIME_M_FORMAT,throwException);
    }

    public static Date parseTime(String textDate) {
        return parseTime(textDate, false);
    }

    public static Date parseTime(String textDate,boolean throwException) {
        return parse(textDate,TIME_FORMAT,throwException);
    }

    public static Date parseTimeM(String textDate) {
        return parseTimeM(textDate, false);
    }

    public static Date parseTimeM(String textDate,boolean throwException) {
        return parse(textDate,TIME_M_FORMAT,throwException);
    }

    public static Date parse(String textDate, String format) {
       return parse(textDate,format,false);
    }

    public static Date parse(String textDate, String format,boolean throwException) {
        if(StringUtils.isEmpty(textDate)){
            return null;
        }
        Date date;
        try {
            DateFormat stored_format = getCachedFormat(format);
            date = stored_format.parse(textDate);

        } catch (Exception e) {
            if (throwException) throw new DateConversionException();
            date = null;
        }
        return date;
    }

    public static String formatDate(Date date) {
        return format(date,DATE_FORMAT,false);
    }
    public static String formatDate(Date date,boolean throwException) {
        return format(date,DATE_FORMAT,throwException);
    }

    public static String formatDateTime(Date date) {
        return format(date,DATETIME_FORMAT,false);
    }
    public static String formatDateTime(Date date,boolean throwException) {
        return format(date,DATETIME_FORMAT,throwException);
    }

    public static String formatDateTimeM(Date date) {
        return format(date,DATETIME_M_FORMAT,false);
    }
    public static String formatDateTimeM(Date date,boolean throwException) {
        return format(date,DATETIME_M_FORMAT,throwException);
    }

    public static String formatTime(Date date) {
        return format(date,TIME_FORMAT,false);
    }
    public static String formatTime(Date date,boolean throwException) {
        return format(date,TIME_FORMAT,throwException);
    }

    public static String formatTimeM(Date date) {
        return format(date,TIME_M_FORMAT,false);
    }
    public static String formatTimeM(Date date,boolean throwException) {
        return format(date,TIME_M_FORMAT,throwException);
    }

    public static String format(Date date, String format) {
        return format(date,format,false);
    }

    public static String format(Date date, String format,boolean throwException) {
        if(date == null){
            return null;
        }
        String formatDate;
        try {
            DateFormat stored_format = getCachedFormat(format);
            formatDate = stored_format.format(date);

        } catch (Exception e) {
            if (throwException) throw new DateConversionException();
            formatDate = null;
        }
        return formatDate;
    }

    public static Date currentDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        return calendar.getTime();
    }

    public static Timestamp currentDateTime() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
        return new Timestamp(calendar.getTime().getTime());
    }


}
