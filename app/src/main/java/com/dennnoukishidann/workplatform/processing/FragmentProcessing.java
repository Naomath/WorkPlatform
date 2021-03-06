package com.dennnoukishidann.workplatform.processing;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.dennnoukishidann.workplatform.login.SignInFragment;
import com.dennnoukishidann.workplatform.login.SignUpFragment;
import com.dennnoukishidann.workplatform.workFragments.RecruitWorkFragment;

/**
 * Created by gotounaoto on 2018/07/11.
 */

public class FragmentProcessing {

    //fragmentを設置する処理

    public static void setUpFragment(Fragment fragment, AppCompatActivity activity, int viewPath, Bundle data) {

        if (data != null) fragment.setArguments(data);

        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(viewPath, fragment);
        transaction.commit();
    }

}
