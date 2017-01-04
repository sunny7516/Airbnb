package com.example.tacademy.airbnb103.Util;

/**
 * 로그 처리
 */
public class Log {
    private static Log ourInstance = new Log();
    public static Log getInstance() {
        return ourInstance;
    }
    private Log() {
    }

    // ture:로그 출력, false:로그 생략
    private final boolean TEST_MODE = true;
    //로그 공통 태그
    private final String TAG = "AB";
    public void log(String msg){
        if(TEST_MODE)   // 상용화할 때는 false로 변경
        android.util.Log.i(TAG, ""+msg);    //""가 null도 문자열로 처리 nullpointer오류 발생하지 않도록
    }
    public void scope(){
        if(TEST_MODE)
            android.util.Log.i(TAG, "=======================");
    }
}
