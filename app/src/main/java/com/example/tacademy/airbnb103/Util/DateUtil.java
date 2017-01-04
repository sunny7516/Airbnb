package com.example.tacademy.airbnb103.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tacademy on 2016-12-30.
 */
public class DateUtil {
    private static DateUtil ourInstance = new DateUtil();

    public static DateUtil getInstance() {
        return ourInstance;
    }

    private DateUtil() {

    }

    public long getCurrentTime(){
        // 현재 시간을 long값으로 return
        // 1970.1.1 00시00분00초부터 현재까지 도래된 시간 + 9
        return System.currentTimeMillis();
    }

    //long의 시간값에서 YYYY-MM-DD를 획득하기
    // "yyyy-MM-dd hh:mm:ss"
    public String getFormatTime(Date date, String format){
        // yyyy-MM-dd hh:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
