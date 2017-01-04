package com.example.tacademy.airbnb103.ui.join;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tacademy.airbnb103.R;
import com.example.tacademy.airbnb103.Util.Alert;
import com.example.tacademy.airbnb103.Util.ObjectStore;
import com.example.tacademy.airbnb103.consts.E;
import com.example.tacademy.airbnb103.model.user.UserInfoVo;
import com.example.tacademy.airbnb103.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class JoinNameActivity extends BaseActivity {

    @BindView(R.id.userLastName)
    EditText userLastName;
    @BindView(R.id.userFirstName)
    EditText userFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_name);
        ButterKnife.bind(this);

        // 상단부 제목 부분 설정
        this.getSupportActionBar().setTitle("");
        // 상단부 키 부분 설정 (Backbutton)
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.OnNextStep)
    public void OnNextStep() {

        String userLastName_str = userLastName.getText().toString();
        String userFirstName_str = userFirstName.getText().toString();

        // 입력값 체크
        if (userLastName == null || userFirstName == null) {
            Toast.makeText(this, "앱을 다시 시작합니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if (userLastName_str.length() == 0) {
            Alert.getInstance().warnAlert(
                    this,
                    "경고",
                    "이름을 정확하게 입력하세요",
                    "확인",
                    new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });
            return;
        }
        if (userFirstName_str.length() == 0) {
            Alert.getInstance().warnAlert(
                    this,
                    "경고",
                    "성을 정확하게 입력하세요",
                    "확인",
                    new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });
            return;
        }
        // 전달
        Intent intent = new Intent(this, JoinPasswordActivity.class);

        UserInfoVo info = new UserInfoVo();
        info.setLastName(userLastName_str);
        info.setFirstName(userFirstName_str);
        //intent.putExtra("userLastName",  userLastName_str);
        //intent.putExtra("userFirstName", userFirstName_str);
        intent.putExtra(E.KEY.USERINFO, info);

        ObjectStore.getInstance().getInfo().setLastName(userLastName_str);
        ObjectStore.getInstance().getInfo().setFirstName(userFirstName_str);

        startActivity(intent);
    }

    // 상단부분 백키를 눌렀을때 이벤트 처리
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}

