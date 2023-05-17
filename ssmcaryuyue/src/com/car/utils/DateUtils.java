// 
// 
// 

package com.car.utils;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DateUtils
{
    public static String newDate() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5 HH:mm:ss");
        final String time = sdf.format(new Date());
        return time;
    }
    
    public static String newDate(final String format) {
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        final String time = sdf.format(new Date());
        return time;
    }
    
    public static Integer getHour(final String date1, final String date2) throws ParseException {
        final double h = 3600000.0;
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date d1 = sdf.parse(date1);
        final Date d2 = sdf.parse(date2);
        return (int)Math.ceil((d2.getTime() - d1.getTime()) / h);
    }
}
