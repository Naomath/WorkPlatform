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

public class SignInFragment extends Fragment implements View.OnClickListener {


    private OnFragmentInteractionListener mListener;

    View mView;

    EditText mEdMailAddress;
    EditText mEdPassword;

    Button mBtnSignIn;
    Button mBtnSignUp;

    boolean mBlEdmailAddress;
    boolean mBlEdPassword;


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
        setUpListeners();
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
                    signIn();
                    break;
                case R.id.sign_up:
                    signUp();
                    break;
            }
        }
    }

    //TextWatcherのクラス

    public class GenericTextWatcher implements TextWatcher {
        //複数edittextの監視のクラス
        View view;

        public GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            switch (view.getId()) {
                case R.id.mail_address:
                    if (charSequence.length() != 0) {
                        mBlEdmailAddress = true;
                    } else {
                        mBlEdmailAddress = false;
                    }
                    break;
                case R.id.password:
                    if (charSequence.length() != 0) {
                        mBlEdPassword = true;
                    } else {
                        mBlEdPassword = false;
                    }
                    break;
            }

            if (mBlEdmailAddress && mBlEdPassword) {
                //両方とも入力されている
                mBtnSignIn.setEnabled(true);
            } else {
                //両方のどちらかが最低でも入力されていない
                mBtnSignIn.setEnabled(false);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }


    //ViewたちのSetUp処理

    public void setUpAllViews(LayoutInflater inflater, ViewGroup container) {
        mView = inflater.inflate(R.layout.fragment_sign_in, container, false);

        mEdMailAddress = (EditText) mView.findViewById(R.id.mail_address);
        mEdPassword = (EditText) mView.findViewById(R.id.password);

        mBtnSignIn = (Button) mView.findViewById(R.id.sign_in);
        mBtnSignUp = (Button) mView.findViewById(R.id.sign_up);
    }

    public void setUpSignUpButton() {
        //SignInのボタンのenableとかをsetUp
        mBtnSignIn.setEnabled(false);
        //ここでfalseにするとselectorが働く
    }

    //リスナーをセッティングする

    public void setUpListeners() {
        mListener = (OnFragmentInteractionListener) getActivity();

        mEdMailAddress.addTextChangedListener(new GenericTextWatcher(mEdMailAddress));
        mEdPassword.addTextChangedListener(new GenericTextWatcher(mEdPassword));

        mBtnSignIn.setOnClickListener(this);
        mBtnSignUp.setOnClickListener(this);
    }

    //ボタンのクリック処理

    public void signIn() {
        String mailAddress = mEdMailAddress.getText().toString();
        String password = mEdPassword.getText().toString();
        //TODO:Write next processing
    }

    public void signUp() {
        mListener.goSinUp();
    }

    //このfragmentのinterface

    public interface OnFragmentInteractionListener {
        void goSinUp();
    }
}
