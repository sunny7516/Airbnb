package com.example.tacademy.airbnb103.Util;

import com.android.volley.RequestQueue;

/**
 * 통신 모듈
 */
public class Net {
    private static Net ourInstance = new Net();

    public static Net getInstance() {
        return ourInstance;
    }

    private Net() {
    }

    // 통신큐 : 요청이 들어오는 순서대로 처리한다.(응답 결과는 비동기)
    private RequestQueue requestQueue;

    public RequestQueue getRequestQueue() {
        if(requestQueue == null){
         requestQueue = new requestQueue();
        }
        return requestQueue;
    }
}
