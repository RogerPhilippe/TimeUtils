package br.com.philippesis;

import java.lang.ref.WeakReference;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtilsCollections {

    private TimeUtilsCollections() {}

    public static final String HOUR_PATTERN_AM_PM = "AM_PM";

    public static final String HOUR_PATTERN_24_H = "24H";

    private String hourPattern = HOUR_PATTERN_24_H;

    private static WeakReference<TimeUtilsCollections> instance = new WeakReference<>(null);

    public static synchronized TimeUtilsCollections getInstance() {
        TimeUtilsCollections m = instance.get();

        if (m != null) return m;

        synchronized (TimeUtilsCollections.class) {
            m = instance.get();
            if (m != null) return m;

            m = new TimeUtilsCollections();

            instance = new WeakReference<>(m);
        }

        return m;
    }

    /**
     *
     * @param hourPattern Hour pattern
     */
    public void setHourPattern(String hourPattern) {
        this.hourPattern = hourPattern;
    }

    /**
     *
     * @return Current Hour in String
     */
    public String getCurrentHours() {
        return currentHours(TimeZone.getDefault());
    }

    /**
     *
     * @param timeZone to get hour defined by timezone
     * @return hour in String
     */
    public String getCurrentHours(String timeZone) {
        return currentHours(TimeZone.getTimeZone(timeZone));
    }

    /**
     *
     * @param timeZone to get hour with defined timezone
     * @return hour in String
     */
    private String currentHours(TimeZone timeZone) {

        // Local variables
        String result = "";
        Calendar calendar = Calendar.getInstance(timeZone);

        if (hourPattern.equals(HOUR_PATTERN_24_H))
            return Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        return Integer.toString(calendar.get(Calendar.HOUR))+" "+ getAMPM(calendar.get(Calendar.AM_PM));
    }

    public String getCurrentMinutes() {
        String currentMinute = "";
        return currentMinute;
    }

    public String getCurrentSeconds() {
        String currentSecond = "";
        return currentSecond;
    }

    public long getCurrentMilliseconds() {
        long currentMilliseconds = 0;
        return currentMilliseconds;
    }

    public String getGMT() {

        // Local variables
        StringBuilder gmtSignedLabel = new StringBuilder();
        String gmtPrefixZero;
        int difference = 0;
        int currentHour = 0;
        int gmtHour = 0;
        Calendar currentCalendar;
        Calendar gmtCalendar;

        currentCalendar = Calendar.getInstance();
        gmtCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY);
        gmtHour = gmtCalendar.get(Calendar.HOUR_OF_DAY);

        difference = currentHour - gmtHour;

        if (difference > 9 || difference < -9) { gmtPrefixZero = ""; }
        else { gmtPrefixZero = "0";}

        if (difference < 0) {
            difference = difference *-1;
            gmtSignedLabel.append("GMT-").append(gmtPrefixZero).append(difference).append(":00");
        } else { gmtSignedLabel.append("GMT+").append(gmtPrefixZero).append(difference).append(":00"); }

        return gmtSignedLabel.toString();
    }

    private String getAMPM(int i) {
        return i == 0 ? "AM" : "PM";
    }

}

