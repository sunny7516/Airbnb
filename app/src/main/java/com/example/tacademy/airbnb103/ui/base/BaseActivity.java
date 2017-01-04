package com.example.tacademy.airbnb103.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    // 공통 기능
/*
    primitive 타입
    byte, short, int, long
    float, double
    boolean
    char
*/
    // 프로그레스바 선언
    private ProgressDialog pd;
    // 프로그레스바 처리

    // 프로그레스바 보이기
    public void showProgress(String msg) {
        //객체를 1회만 생성한다.
        if (pd == null) {
            //ProgressDialog 생성
            pd = new ProgressDialog(this);
            //back key로 닫는 기능 제거
            pd.setCancelable(false);
        }
        //원하는 메세지 세팅
        pd.setMessage(msg);
        //화면에 띄워라
        pd.show();
    }

    // 프로그레스바 숨기기
    public void hideProgress(){
        //닫는다 : 객체가 존재하고, 보일 때만
        if( pd != null && pd.isShowing() ) {
            pd.dismiss();
        }
    }
}
