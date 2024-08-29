package DateHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UnixTimeConverter {

    public static String convertUnixToDayTime(long unixTimestamp) {
        Date date = new Date(unixTimestamp);
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        dayFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        String day = dayFormat.format(date);
        String time = timeFormat.format(date);

        return day + " " + time;
    }

    public static void printUnixDate(long timeStamp) {
        Date date = new Date(timeStamp * 1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        String java_date = jdf.format(date);
        System.out.println("\n" + java_date + "\n");
    }
}
