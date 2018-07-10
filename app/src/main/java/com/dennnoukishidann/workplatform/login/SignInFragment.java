package com.dennnoukishidann.workplatform.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dennnoukishidann.workplatform.R;

public class SignInFragment extends Fragment implements View.OnClickListener, TextWatcher {


    private OnFragmentInteractionListener mListener;

    View mView;

    EditText mEdMail;
    EditText mEdPassword;

    Button mBtSignIn;
    Button mBtSignUp;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //Fragmentのメソッド

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpAllViews(inflater, container);
        setUpSignUpButton();
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //OnClickListenerのメソッド

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.sign_in:
                    break;
                case R.id.sign_up:
                    break;
            }
        }
    }

    //TextWatcherのメソッド

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    //ViewたちのSetUp処理

    public void setUpAllViews(LayoutInflater inflater, ViewGroup container) {
        mView = inflater.inflate(R.layout.fragment_sign_in, container, false);

        mEdMail = (EditText) mView.findViewById(R.id.mail_address);
        mEdPassword = (EditText) mView.findViewById(R.id.password);
        mEdMail.addTextChangedListener(this);
        mEdPassword.addTextChangedListener(this);

        mBtSignIn = (Button) mView.findViewById(R.id.sign_in);
        mBtSignUp = (Button) mView.findViewById(R.id.sign_up);
    }

    public void setUpSignUpButton() {
        //SignInのボタンの背景とかをちゃんと設定する

    }

    //ボタンのクリック処理

    public void signIn() {
        String mailAddress = mEdMail.getText().toString();
        String password = mEdPassword.getText().toString();
    }

    public interface OnFragmentInteractionListener {
    }
}
