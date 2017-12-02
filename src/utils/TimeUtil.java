package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gavin on 2017/11/16.
 */
public class TimeUtil {

    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
