package com.dennnoukishidann.workplatform.processing;

import android.content.Context;
import android.content.SharedPreferences;

import com.dennnoukishidann.workplatform.R;
import com.dennnoukishidann.workplatform.enums.LoginStatus;

/**
 * Created by gotounaoto on 2018/07/11.
 */

public class SavingDataProcessing {

    //保存に関する処理

    public static String LOGIN_STATUS_SP_KEY = "LoginStatus";

    public static String LOGIN_STATUS_VALUE_KEY = "LoginStatus";

    public static String USER_ID_VALUE_KEY = "UserId";

    //SharePreferenceの呼び出し

    public static SharedPreferences returnLoginStatusSp(Context context) {
        return context.getSharedPreferences(LOGIN_STATUS_SP_KEY, Context.MODE_PRIVATE);
    }

    //実際の保存してあるvalueの呼び出し

    public static LoginStatus returnLoginStatus(Context context) {
        boolean blLoginStatus = returnLoginStatusSp(context).getBoolean(LOGIN_STATUS_VALUE_KEY, false);

        if (blLoginStatus) {
            return LoginStatus.IN;
        } else {
            return LoginStatus.OUT;
        }
    }

    public static String returnUserId(Context context) {
        //userIDをリターンする
        return returnLoginStatusSp(context).getString(USER_ID_VALUE_KEY, null);
    }

    //保存する処理
    public static void saveLoginStatus(Context context, LoginStatus which) {
        SharedPreferences.Editor editor = returnLoginStatusSp(context).edit();

        switch (which) {
            case IN:
                editor.putBoolean(LOGIN_STATUS_VALUE_KEY, true);
                break;

            case OUT:
                editor.putBoolean(LOGIN_STATUS_VALUE_KEY, false);
                break;
        }

        editor.apply();
    }

    public static void saveUserId(Context context, String userId) {
        //これはログイン状態になってる時のためにuserIdを保存する
        SharedPreferences.Editor editor = returnLoginStatusSp(context).edit();

        editor.putString(USER_ID_VALUE_KEY, userId);

        editor.apply();
    }

}
