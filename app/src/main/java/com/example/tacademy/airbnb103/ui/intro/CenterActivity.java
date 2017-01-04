package com.example.tacademy.airbnb103.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tacademy.airbnb103.R;
import com.example.tacademy.airbnb103.ui.base.BaseActivity;
import com.example.tacademy.airbnb103.ui.join.JoinNameActivity;
import com.example.tacademy.airbnb103.ui.join.TermsActivity;

public class CenterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
    }

    // 로그인 클릭 시 호출
    public void onLogin(View view){
        Toast.makeText(this, "로그인 버튼 클릭", Toast.LENGTH_SHORT).show();
    }
    // 페이스북 클릭 시 호출
    public void onFacebook(View view){
        Toast.makeText(this, "페이스북 버튼 클릭", Toast.LENGTH_SHORT).show();
    }
    // 회원가입 클릭 시 호출
    public void onJoin(View view){
        Toast.makeText(this, "회원가입 버튼 클릭", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, JoinNameActivity.class);
        startActivity(intent);
    }
    // 이용약관 클릭 시 호출
    public void onTerms(View view){
        Toast.makeText(this, "이용약관 버튼 클릭", Toast.LENGTH_SHORT).show();
        //이용약관 화면으로 이동
        Intent intent = new Intent(this, TermsActivity.class);
        startActivity(intent);
    }
}
