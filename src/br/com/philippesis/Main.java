package br.com.philippesis;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {
	// write your code here

//        String timeZoneID = "GMT-03:00";
//        String timeZone = "America/Sao_Paulo";
//
//        // Example TimeZone = GTM-03:00 or America/Sao_Paulo
//        System.out.printf("Hour 24H pattern: %s\n\n",
//                TimeUtilsCollections.getInstance().getCurrentHours(timeZoneID));
//
//        // Set Time Pattern to AM_PM
//        TimeUtilsCollections.getInstance().setHourPattern(TimeUtilsCollections.HOUR_PATTERN_AM_PM);
//        System.out.printf("Hour AM PM pattern: %s\n\n",
//                TimeUtilsCollections.getInstance().getCurrentHours(timeZoneID));
//
//        System.out.printf("\nGMT from lib: %s", TimeUtilsCollections.getInstance().getGMT());

        System.out.println("GMT: "+getGMT());

    }

    private static String getGMT() {

//        Calendar currentCalendar;
//        Calendar gmtCalendar;
//
//        currentCalendar = Calendar.getInstance();
//        gmtCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
//
//        String time1 = currentCalendar.get(Calendar.HOUR_OF_DAY)+":00:00";
//        String time2 = gmtCalendar.get(Calendar.HOUR_OF_DAY)+":00:00";
//
//        int day1 = currentCalendar.get(Calendar.DAY_OF_MONTH);
//        int day2 = gmtCalendar.get(Calendar.DAY_OF_MONTH);

        String time1 = "04:41:00";
        String time2 = "11:11:00";
        String minuteString = "00";

        int day1 = 20;
        int day2 = 21;

        String result = "";

        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);

            long difference = date2.getTime() - date1.getTime();

            boolean compareDate = date1.before(date2);
            boolean compareDays = day1 == day2;

            long hour = (difference / 3600) / 1000;
            long minute = difference / (1000*60) % 60;

            if (minute != 0) {
                if (minute < 0) minute = minute *-1;
                if (minute > 9) minuteString = String.valueOf(minute);
                else minuteString = "0" + minute;
            }

            if (hour < -13) hour = 24 + hour;

            if (hour > 13) hour = 24 - hour;

            String signal = "+";

            if (compareDate && compareDays || !compareDate && !compareDays) signal = "-";

            if (hour < 0) hour = hour * -1;

            String prefixZero = "";
            if (hour < 9) prefixZero = "0";

            result = "GMT"+signal+prefixZero+hour+":"+minuteString;

        } catch (Exception ex) {}

        return result;

    }

}
