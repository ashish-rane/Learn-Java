package com.xpert.java;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DateTimeDemo {

    public static  void main(String[] args){

        // Now Local
        DateTime dt = new DateTime();
        System.out.println(dt + "Timezone :" + dt.getZone());

        // Any Date Time Local
        DateTime dt2 = new DateTime(2018, 04,8,11,30,55, DateTimeZone.getDefault());
        System.out.println(dt2.toString("yyyy/MM//dd HH:mm:ss ") + "Time zone " + dt2.getZone());

        System.out.println(dt2.toDateTime(DateTimeZone.UTC));

        System.out.println(" Unix Time " + dt2.getMillis());
        System.out.println("Unix Time UTC " + dt2.toDateTime(DateTimeZone.UTC).getMillis());

        System.out.println("IsBefore Now " + dt2.isBeforeNow());

        // Add - Substract
        System.out.println("Tomorrow " + dt.plusDays(1));
        System.out.println("Yesterday " + dt.minusDays(1));

        // First day of month
        System.out.println("1st of month " + dt.withDayOfMonth(1));

        // NOTE: LocalDateTime, LocalDate and LocalTime are the equivalent classes
        // of DateTime, Date and Time classes with no timezone information.
        // DateTime, Date, Time are timezone aware.

        // Duration is just a length of time either expressed in seconds milliseconds minutes etc...

        // Periods - used for calculating differences or a time interval between two times.
        // its a representation of a duration.
        DateTime beforeDST = new DateTime(2013, 3, 10, 1, 45);
        DateTime afterDST = new DateTime(2013, 3, 10, 3, 45);
        Period p = new Period(beforeDST, afterDST);
        System.out.println("minutes between 1:45 and 3:45: " +                p.getMinutes());


        DateTime departure = new DateTime(2013, 4, 1, 5, 0, DateTimeZone.forID("America/New_York"));
        DateTime arrival =   new DateTime(2013, 4, 1, 8, 0, DateTimeZone.forID("America/Los_Angeles"));
        Period tripLength = new Period(departure, arrival);
        System.out.println("Trip length in hours: " +  tripLength.getHours());

        // Date Time from num of millseconds
        // Unix time in seconds * 1000 => milliseconds. NOTE The 'L' at the end is important.
        DateTime dt3 = new DateTime(1523197855 *1000L, DateTimeZone.UTC);
        System.out.println(dt3);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt4 = formatter.parseDateTime("2017-12-14 14:27:18");
        System.out.println(dt4.toString() + " Time zone " + dt4.getZone());
    }

}
