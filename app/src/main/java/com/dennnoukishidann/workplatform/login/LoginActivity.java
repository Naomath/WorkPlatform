package com.dennnoukishidann.workplatform.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dennnoukishidann.workplatform.R;
import com.dennnoukishidann.workplatform.enums.LoginStatus;
import com.dennnoukishidann.workplatform.processing.FragmentProcessing;
import com.dennnoukishidann.workplatform.processing.SavingDataProcessing;
import com.dennnoukishidann.workplatform.processing.TransitionToActivitiesProcessing;

import static com.dennnoukishidann.workplatform.enums.LoginStatus.IN;

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
        if (mStatus == IN) {
            //ログインしている時の処理
            transitionToMainActivity(null);
        } else {
            //ログインしてない時の処理
            setUpFragment(SIGN_IN);
        }

    }

    //SignInFragmentのinterfaceのメソッド

    @Override
    public void goSinUp() {
        //『新規登録』ボタンが押された時の処理
        setUpFragment(SIGN_UP);
    }

    @Override
    public void loginFromSignIn(String userId) {
        //signInFragmentでsignInボタンが押された時の処理
        transitionToMainActivity(userId);
    }

    //SignUpFragmentのinterfaceのメソッド

    @Override
    public void cancelSignUp() {
        //キャンセルボタンがSignUpFragmentで押された時の処理
        setUpFragment(SIGN_IN);
    }

    @Override
    public void loginFromSignUp(String userId) {
        //signUpFragmentでユーザー登録が完了してサインインする時の処理
        transitionToMainActivity(userId);
    }

    //ログイン状況を確認するメソッド

    public void checkLoginStatus() {
        mStatus = SavingDataProcessing.returnLoginStatus(this);
    }

    //Fragmentに関する処理


    public void setUpFragment(int which) {
        int viewPath = R.id.frame;

        if (which == SIGN_IN) {
            FragmentProcessing.setUpFragment(SignInFragment.newInstance(), this, viewPath, null);
        } else if (which == SIGN_UP) {
            FragmentProcessing.setUpFragment(SignUpFragment.newInstance(), this, viewPath, null);
        }
    }

    //LoginしてMainActivityに移る処理

    public void transitionToMainActivity(String userId) {
        //ここでif文をかけることでログイン状態の時は、不必要に
        //userIdを保存することを無くしている

        if (userId != null) {
            //まずはログイン状態にしてuserIDを保存する
            //サインインするときに使う
            SavingDataProcessing.saveLoginStatus(this, IN);

            SavingDataProcessing.saveUserId(this, userId);
        }

        //それからMainActivityに移る
        //具体的にデーターとかをとる処理はMainActivityにやらせる
        TransitionToActivitiesProcessing.fromLoginToMain(this);
    }

}
