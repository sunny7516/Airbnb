package com.example.tacademy.airbnb103.ui.join;

import android.os.Bundle;

import com.example.tacademy.airbnb103.R;
import com.example.tacademy.airbnb103.Util.Log;
import com.example.tacademy.airbnb103.consts.E;
import com.example.tacademy.airbnb103.model.user.UserInfoVo;
import com.example.tacademy.airbnb103.ui.base.BaseActivity;

public class JoinProcessActivity extends BaseActivity {

    UserInfoVo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_process);

        info = (UserInfoVo) this.getIntent().getSerializableExtra(E.KEY.USERINFO);

        Log.getInstance().scope();
        Log.getInstance().log(info.toString());
        Log.getInstance().scope();

        // 나머지 정보 설정
        // 토큰, 전번, uuid, 단말기 모델, os 정보

        // 서버로 회원가입 정보 전송
        // 전문 구성 (프로토콜 형식에 맞춰서) -> Clas 설계 -> 모델링
        // JSON 변환
        // 암호화+체크성
        // 통신
        // 성공이면 -> 본서비스 이동
    }
}
