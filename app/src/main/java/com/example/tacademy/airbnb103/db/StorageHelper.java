package com.example.tacademy.airbnb103.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tacademy.airbnb103.consts.E;

/**
 * 앱 구동간 필요에 의해서 저장하는 저장소 기능
 */
public class StorageHelper {
    private static StorageHelper ourInstance = new StorageHelper();

    public static StorageHelper getInstance() {
        return ourInstance;
    }

    private StorageHelper() {
    }

    // ========================= 저장 타입별 기능 제공 ===========================
/*
    static {
        // NDK로 만들어는 lib, o 등등 파일을(c/c++ 라이브러리) 로드할 때 사용
        // libxxxxxx.o
        // System.loadLibrary("xxxx.o");
    }
*/
    public void setString(Context context, String key, String value) {
        // 저장소 획득
        SharedPreferences.Editor editor = context.getSharedPreferences(E.KEY.STORAGE_KEY, 0).edit();
        // 저장
        editor.putString(key, value);
        // 커밋
        editor.commit();
    }

    public String getString(Context context, String key) {
        return
                context.getSharedPreferences(E.KEY.STORAGE_KEY, 0).getString(key, "");
    }

    public void setBoolean(Context context, String key, Boolean value) {
        // 저장소 획득
        SharedPreferences.Editor editor = context.getSharedPreferences(E.KEY.STORAGE_KEY, 0).edit();
        // 저장
        editor.putBoolean(key, value);
        // 커밋
        editor.commit();
    }

    public Boolean getBoolean(Context context, String key) {
        return
                context.getSharedPreferences(E.KEY.STORAGE_KEY, 0).getBoolean(key, false);
    }
}
