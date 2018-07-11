package com.dennnoukishidann.workplatform.processing;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import com.dennnoukishidann.workplatform.R;
import com.dennnoukishidann.workplatform.login.SignInFragment;

/**
 * Created by gotounaoto on 2018/07/11.
 */

public class FragmentProcessing {

    //fragmentを設置する処理

    public static void setUpSignIn(Bundle data, AppCompatActivity activity, int viewPath){
        //SignInFragmentのセットアップ
        SignInFragment fragment = new SignInFragment();

        if(data!=null) fragment.setArguments(data);

        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(viewPath, fragment);
        transaction.commit();

    }

}
