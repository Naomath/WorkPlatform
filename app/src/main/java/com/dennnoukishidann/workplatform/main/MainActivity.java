package com.dennnoukishidann.workplatform.main;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dennnoukishidann.workplatform.R;
import com.dennnoukishidann.workplatform.enums.LoginStatus;
import com.dennnoukishidann.workplatform.processing.SavingDataProcessing;
import com.dennnoukishidann.workplatform.processing.TransitionToActivitiesProcessing;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn =  (Button) findViewById(R.id.log_out);
        final Context context = (Context) this;
        final Activity activity = (Activity) this;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavingDataProcessing.saveLoginStatus(context, LoginStatus.OUT);
                TransitionToActivitiesProcessing.fromMainToLogin(activity);
            }
        });
    }
}
