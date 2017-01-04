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

public class JoinEmailActivity extends BaseActivity {

    @BindView(R.id.userMail)
    EditText userMail;

    UserInfoVo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_email);
        ButterKnife.bind(this);

        info = (UserInfoVo)this.getIntent().getSerializableExtra(E.KEY.USERINFO);

        Log.getInstance().scope();
        Log.getInstance().log(info.toString());
        Log.getInstance().scope();
    }

    // 다음 단계 이동
    @OnClick(R.id.button_next)
    public void submit() {

        String userMail_str = userMail.getText().toString();

        if (userMail == null) {
            Toast.makeText(this, "앱을 다시 실행합니다.", Toast.LENGTH_SHORT);
            finish();
            return;
        }

        if (userMail_str.length() == 0) {
            Alert.getInstance().warnAlert(
                    this,
                    "경고",
                    "이메일을 정확하게 입력하세요",
                    "확인",
                    new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });
            return;
        }

        info.setEmail(userMail_str);
        ObjectStore.getInstance().getInfo().setEmail(userMail_str);

        Intent intent = new Intent(this, JoinProcessActivity.class);
        intent.putExtra(E.KEY.USERINFO, info);
        startActivity(intent);
    }
}
