package com.dennnoukishidann.workplatform.processing;

import android.app.Activity;
import android.content.Intent;

import com.dennnoukishidann.workplatform.main.MainActivity;

/**
 * Created by gotounaoto on 2018/07/14.
 */

public class TransitionToActivitiesProcessing {
    //Activityの遷移するときの処理

    public static void fromLoginToMain(Activity activity) {
        //LoginActivityからMainActivityへと
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

}
