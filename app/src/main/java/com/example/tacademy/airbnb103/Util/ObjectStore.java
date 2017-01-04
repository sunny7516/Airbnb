package com.example.tacademy.airbnb103.Util;

import com.example.tacademy.airbnb103.model.user.UserInfoVo;

/**
 * 앱상에서 전역적으로 객체에 무관하게
 * 고유한 하나의 정보들을 저장하는 싱글톤
 */

public class ObjectStore {
    private static ObjectStore ourInstance = new ObjectStore();

    public static ObjectStore getInstance() {
        return ourInstance;
    }

    private ObjectStore() {
    }

    // 회원가입 정보(유저정보)
    private UserInfoVo info;

    public UserInfoVo getInfo() {
        if(info==null) info = new UserInfoVo();
        return info;
    }
}
