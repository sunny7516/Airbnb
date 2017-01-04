package com.example.tacademy.airbnb103.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.tacademy.airbnb103.BuildConfig;
import com.example.tacademy.airbnb103.R;
import com.example.tacademy.airbnb103.Util.DateUtil;
import com.example.tacademy.airbnb103.consts.E;
import com.example.tacademy.airbnb103.db.StorageHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.Date;

public class ServiceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // 1. 선언
    FirebaseRemoteConfig mFirebaseRemoteConfig;
    ServiceActivity self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        self = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 하단에 편지 아이콘
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        // 클릭하면 이벤트 발생
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // 왼쪽 sideview 토글 이벤트 등록
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // 왼쪽 navigation view의 item 선택 시 이벤트를 처리하게끔 listener 등록
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //firebase 원격 구성을 이용한 자체 광고 띄우기
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
                showAD(mFirebaseRemoteConfig.getString(E.KEY.MAIN_AD));
            }
        });
    }

    public void showAD(String url) {
        if (url == null) return;    //null이면 중단
        if (url.length() < 10) return;  //url이 작으면 중단
        //오늘 광고를 안보기로 했으면 중단
        String today_str = DateUtil.getInstance().getFormatTime(new Date(), E.KEY.TODAY_SAVE_DATE_FORMAT);
        String saved_str
                = StorageHelper.getInstance().getString(self, E.KEY.TODAY_OK_KEY);
        if (today_str != null && saved_str != null)
            if (today_str.equals(saved_str)) return; //시간이 동일하면 중단


        // 자체 팝업을 띄우는데 그 내용을 웹뷰로 재정의
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 웹뷰만 그냥 띄우기
        /*
        WebView web = new WebView(this);
        web.loadUrl(url);
        builder.setView(web);
        */

        // 커스텀뷰로 띄우기 --------------------------------------
        // 1. xml로 구성된 레이아웃을 자바 코드상의 View로 만드는 클래스
        LayoutInflater inflater = this.getLayoutInflater();
        LayoutInflater inflater2 = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 2. view 생성
        View view = inflater.inflate(R.layout.custom_popup_ad_layout, null);

        WebView webView = (WebView) view.findViewById(R.id.webView);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        Button closeBtn = (Button) view.findViewById(R.id.closeBtn);

        // 4. 컴포넌트별 기능 세팅
        webView.loadUrl(url);

        // 5. 커스텀뷰 설정
        builder.setView(view);
        //ButterKnife.bind(this, view);
        // -----------------------------------------------------
        final AlertDialog alert = builder.create();
        alert.show();
        // alert를 제어하기 위해 마지막에 설정
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 팝업을 닫는다.
                alert.dismiss();
                // [실습] 체크가 되었다면 yyyy-MM-dd 저장
                if (checkBox.isChecked()) {
                    StorageHelper.getInstance().setString(self, E.KEY.TODAY_OK_KEY, DateUtil.getInstance().getFormatTime(new Date(), E.KEY.TODAY_SAVE_DATE_FORMAT));
                }

                /*
               checkBox.isChecked() ?
                        StorageHelper.getInstance().setBoolean(self, E.KEY.TODAY_OK_KEY, true) :
                        StorageHelper.getInstance().setBoolean(self, E.KEY.TODAY_OK_KEY, false);
                */
                // Log.getInstance().log(""+checkBox.isChecked());
            }
        });
    }

    // back key 누르면 호출
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // 왼쪽 메뉴바가 화면에 보이면
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // 화면에 안보이면 그냥 종료
            // 1번 클릭 -> 한번더 누르시면 종료됩니다.
            // 2초 이내에 누르면 -> 종료
            // 2초 초과되면 -> 한번더 누르시면 종료됩니다.
            super.onBackPressed();
        }
    }

    // 상단 메뉴 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // 상단 menu상에 존재하는 item 선택하면 호출
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // side menu item 선택하면 호출된다.
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
