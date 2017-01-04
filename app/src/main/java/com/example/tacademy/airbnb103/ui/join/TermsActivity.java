package com.example.tacademy.airbnb103.ui.join;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.tacademy.airbnb103.R;
import com.example.tacademy.airbnb103.ui.base.BaseActivity;

public class TermsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        WebView webView =(WebView)this.findViewById(R.id.webView);  //java 다형성
        // 1. 자바스크립트 허용 처리
        // 2. 크롬 클라이언트 등 구현 처리
        // 3. 로딩
        webView.loadUrl("https://nid.naver.com/user2/common/terms/terms.nhn?m=viewTermOfUseNaver&mobile=Y");
    }
}