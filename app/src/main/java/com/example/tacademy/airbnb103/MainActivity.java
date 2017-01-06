package com.example.tacademy.airbnb103;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.tacademy.airbnb103.Util.Alert;
import com.example.tacademy.airbnb103.Util.ImageProc;
import com.example.tacademy.airbnb103.consts.E;
import com.example.tacademy.airbnb103.test.Tap2Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {
    // 1. 선언
    FirebaseRemoteConfig mFirebaseRemoteConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        // 2. 초기화
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings settings =
                new FirebaseRemoteConfigSettings.Builder()
                        .setDeveloperModeEnabled(BuildConfig.DEBUG)
                        .build();
        // 3. 구성 설정
        mFirebaseRemoteConfig.setConfigSettings(settings);
        // 4. 원격 구성정보 획득
        onRemoteConfigLoad();
    }

    public void onRemoteConfigLoad() {
        mFirebaseRemoteConfig.fetch(0).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task task) {
                // 실패하면 아무것도 하지 않는다.
                if (!task.isSuccessful()) {
                    return;
                }
                mFirebaseRemoteConfig.activateFetched();
                // 매개변수를 호출한다.

                // 2017.01.04 ====================================
                // 리얼 도메인 획득
                E.NET.REAL_DOMAIN = mFirebaseRemoteConfig.getString(E.KEY.REAL_DOMAIN_KEY);
                // 테스트 도메인 획득
                E.NET.TEST_DOMAIN = mFirebaseRemoteConfig.getString(E.KEY.TEST_DOMAIN_KEY);
                // 테스트 모드 플레그값 획득
                E.NET.TEST_MODE = mFirebaseRemoteConfig.getBoolean(E.KEY.TEST_MODE_KEY);
                if(E.NET.TEST_MODE){
                    E.NET.USE_DOMAIN = E.NET.TEST_DOMAIN;
                }else{
                    E.NET.USE_DOMAIN = E.NET.REAL_DOMAIN;
                }
                // ===============================================
                // 정상

                // 긴급 공지 메세지 획득
                String msg =
                        mFirebaseRemoteConfig.getString(E.KEY.EMERGENCY_KEY);

                if (msg == null || msg.length() == 0 || msg.equals("")) {
                    goNextActivity();
                } else {
                    Alert.getInstance().warnAlert(
                            MainActivity.this,
                            "긴급공지",
                            msg,
                            "종료",
                            new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                    // 앱 종료
                                    finish();
                                }
                            }
                    );
                }
            }
        });
    }

    public void goNextActivity() {
        // 이미지 프로세스 처리 모듈 초기화
        ImageProc.getInstance().getImageLoader(this);

        //인트로 화면으로 이동(Intent)
        Intent intent = new Intent(this, Tap2Activity.class);
        //화면 전환 수행
        startActivity(intent);
        // 화면을 닫는다
        finish();
    }

}
