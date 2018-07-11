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

    //SharePreferenceの呼び出し

    public static SharedPreferences getLoginStatusSp(Context context) {
        return context.getSharedPreferences(String.valueOf(R.string.SpLoginStatusKey), Context.MODE_PRIVATE);
    }

    //実際の保存してあるvalueの呼び出し

    public static LoginStatus getLoginStatus(Context context) {
        boolean blLoginStatus = getLoginStatusSp(context).getBoolean(String.valueOf(R.string.SpLoginStatusValueKey), false);
        if(blLoginStatus){
            return LoginStatus.IN ;
        }else {
            return LoginStatus.OUT;
        }
    }

}
