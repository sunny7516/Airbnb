package com.example.tacademy.airbnb103.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.tacademy.airbnb103.R;
import com.example.tacademy.airbnb103.ui.base.BaseActivity;

public class IntroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Log.i("AB", "로딩시작");
        showProgress("--loading--");
        Log.i("AB", "로딩진행중...");
        //통신 진행 : 버전체크 (업데이트 유도)
        //2초 후에 로딩 닫고 center로 이동한다.
        handler.sendEmptyMessageDelayed(0,1000*2);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){  //0번 메세지 처리
                //로딩 닫고
                hideProgress();
                //센터로 이동
                Intent intent = new Intent(IntroActivity.this, CenterActivity.class);
                //화면 전환 수행
                startActivity(intent);
            }
        }
    };

/*
    상속받아서 재정의 (Override)하는 것을 줄여서 위처럼 사용
    MyHandler h = new MyHandler();
    class MyHandler extends Handler{

    }
*/

}
