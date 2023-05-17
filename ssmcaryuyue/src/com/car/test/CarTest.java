// 
// 
// 

package com.car.test;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.testng.annotations.Test;

public class CarTest
{
    @Test
    public void testSubstring() {
        String s = "20190312001";
        s = s.substring(8);
        System.out.println(s);
    }
    
    @Test
    public void testHour() throws ParseException {
        final String date1 = "2018-01-02 09:00:01";
        final String date2 = "2018-01-02 10:00:00";
        final double h = 3600000.0;
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date d1 = sdf.parse(date1);
        final Date d2 = sdf.parse(date2);
        final long time1 = d1.getTime();
        final long time2 = d2.getTime();
        final double tempH = Math.ceil((time2 - time1) / h);
        final int huor = (int)tempH;
        System.out.println(huor);
    }
}
