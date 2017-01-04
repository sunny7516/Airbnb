package com.example.tacademy.airbnb103.Util;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 알러트, 팝업용 유틸리티
 */

public class Alert {
    private static Alert ourInstance = new Alert();

    public static Alert getInstance() {
        return ourInstance;
    }

    private Alert() {
    }   //생성자

    // 경고 Alert
    public SweetAlertDialog warnAlert(Context context,
                                        String title, String msg, String confirm,
                                        SweetAlertDialog.OnSweetClickListener listener) {
        SweetAlertDialog alert =
                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(title)
                        .setContentText(msg)
                        .setConfirmText(confirm)
                        .setConfirmClickListener(listener);
        alert.show();
        return alert;
    }
}
