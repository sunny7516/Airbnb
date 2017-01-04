package com.example.tacademy.airbnb103.ui.join;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tacademy.airbnb103.R;
import com.example.tacademy.airbnb103.Util.Alert;
import com.example.tacademy.airbnb103.Util.Log;
import com.example.tacademy.airbnb103.Util.ObjectStore;
import com.example.tacademy.airbnb103.consts.E;
import com.example.tacademy.airbnb103.model.user.UserInfoVo;
import com.example.tacademy.airbnb103.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class JoinPasswordActivity extends BaseActivity {

    @BindView(R.id.userPassword)
    EditText userPassword;

    UserInfoVo info;
    //String userLastName, userFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_password);

        // butterknife binding
        ButterKnife.bind(this);

        info = (UserInfoVo)this.getIntent().getSerializableExtra(E.KEY.USERINFO);
        // Activity 호출할 때 전달한 데이터를 받는 방법
        //userLastName = this.getIntent().getStringExtra("userLastName");
        //userFirstName= this.getIntent().getStringExtra("userFirstName");

        Log.getInstance().scope();
        Log.getInstance().log(info.toString());
        Log.getInstance().scope();
    }

    @OnClick(R.id.submit)
    public void submit() {
        String userPassword_str = userPassword.getText().toString();

        // 값 체크
        // 해당 객체가 바인딩이 되었는지 체크
        if (userPassword == null) {
            Toast.makeText(this, "앱을 다시 실행합니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 실제 값 체크
        if (userPassword_str.length() == 0) {
            Alert.getInstance().warnAlert(
                    this,
                    "경고",
                    "패스워드를 정확하게 입력하세요",
                    "확인",
                    new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });
            return;
        }
        // 값의 유효성 검사(패턴식)(8자 이상 특수문자, 영소영대가 반드시 포함)
        // 길이 검사
        // 암호화 (비밀번호, 전화번호) >> 마지막 구현사항

        info.setPassword(userPassword_str);
        ObjectStore.getInstance().getInfo().setPassword(userPassword_str);

        Intent intent = new Intent(this, JoinEmailActivity.class);
        intent.putExtra(E.KEY.USERINFO, info);
        startActivity(intent);
    }
}
