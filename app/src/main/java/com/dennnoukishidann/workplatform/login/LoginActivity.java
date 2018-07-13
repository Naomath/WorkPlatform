package com.dennnoukishidann.workplatform.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dennnoukishidann.workplatform.R;
import com.dennnoukishidann.workplatform.enums.LoginStatus;
import com.dennnoukishidann.workplatform.processing.FragmentProcessing;
import com.dennnoukishidann.workplatform.processing.SavingDataProcessing;

public class LoginActivity extends AppCompatActivity
        implements SignInFragment.OnFragmentInteractionListener, SignUpFragment.OnFragmentInteractionListener {

    LoginStatus mStatus;

    public static int SIGN_IN = 0;
    public static int SIGN_UP = 1;

    //activityのライフサイクル

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkLoginStatus();
        if (mStatus == LoginStatus.IN) {
            //ログインしている時の処理
            //TODO:Write Next Processing on Main
        } else {
            //ログインしてない時の処理
            setUpFragment(SIGN_IN);
        }

    }

    //SignInFragmentのinterfaceのメソッド

    @Override
    public void goSinUp() {
        setUpFragment(SIGN_UP);
    }

    //SignUpFragmentのinterfaceのメソッド

    //ログイン状況を確認するメソッド

    @Override
    public void cancelSignUp() {
        setUpFragment(SIGN_IN);
    }

    public void checkLoginStatus() {
        mStatus = SavingDataProcessing.getLoginStatus(this);
    }

    //Fragmentに関する処理
    public void setUpFragment(int which) {
        int viewPath = R.id.frame;

        if (which == SIGN_IN) {
            FragmentProcessing.setUpSignIn(null, this, viewPath);
        } else if (which == SIGN_UP) {
            FragmentProcessing.setUpSignUp(null, this, viewPath);
        }
    }

}
