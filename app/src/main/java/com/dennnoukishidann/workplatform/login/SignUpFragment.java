package com.dennnoukishidann.workplatform.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dennnoukishidann.workplatform.R;

public class SignUpFragment extends Fragment implements View.OnClickListener {


    private OnFragmentInteractionListener mListener;

    View mView;

    ImageButton mBtnDone;

    Button mBtnCancel;

    EditText mEdUserName;
    EditText mEdMailAddress;
    EditText mEdPassword;
    EditText mEdRePassword;

    boolean mBlEdUserName;
    boolean mBlEdMailAddress;
    boolean mBlEdPassword;
    boolean mBlEdRePassword;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    //Fragmentのライフサイクルの部分

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpViews(inflater, container);
        setUpDoneButton();
        setUpListeners();
        return mView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //OnClickListenerのメソッド

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.done:
                    //TODO:write next processing
                    break;
                case R.id.cancel:
                    cancel();
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
                case R.id.user_name:
                    if (charSequence.length() != 0) {
                        mBlEdUserName = true;
                    } else {
                        mBlEdUserName = false;
                    }
                    break;

                case R.id.mail_address:
                    if (charSequence.length() != 0) {
                        mBlEdMailAddress = true;
                    } else {
                        mBlEdMailAddress = false;
                    }
                    break;

                case R.id.password:
                    if (charSequence.length() != 0) {
                        mBlEdPassword = true;
                    } else {
                        mBlEdPassword = false;
                    }
                    break;

                case R.id.re_password:
                    if (charSequence.length() != 0) {
                        mBlEdRePassword = true;
                    } else {
                        mBlEdRePassword = false;
                    }
                    break;
            }

            if (mBlEdUserName && mBlEdMailAddress && mBlEdPassword && mBlEdRePassword) {
                //両方とも入力されている
                mBtnDone.setEnabled(true);
            } else {
                //両方のどちらかが最低でも入力されていない
                mBtnDone.setEnabled(false);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }


    //ViewたちのSetUp
    public void setUpViews(LayoutInflater inflater, ViewGroup container) {
        mView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        mBtnDone = (ImageButton) mView.findViewById(R.id.done);
        mBtnCancel = (Button) mView.findViewById(R.id.cancel);

        mEdUserName = (EditText) mView.findViewById(R.id.user_name);
        mEdMailAddress = (EditText) mView.findViewById(R.id.mail_address);
        mEdPassword = (EditText) mView.findViewById(R.id.password);
        mEdRePassword = (EditText) mView.findViewById(R.id.re_password);
    }

    public void setUpDoneButton() {
        //SignInのボタンのenableとかをsetUp
        mBtnDone.setEnabled(false);
        //ここでfalseにするとselectorが働く
    }

    //リスナーの設定
    public void setUpListeners() {
        mListener = (OnFragmentInteractionListener) getActivity();

        mBtnDone.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        mEdUserName.addTextChangedListener(new GenericTextWatcher(mEdUserName));
        mEdMailAddress.addTextChangedListener(new GenericTextWatcher(mEdMailAddress));
        mEdPassword.addTextChangedListener(new GenericTextWatcher(mEdPassword));
        mEdRePassword.addTextChangedListener(new GenericTextWatcher(mEdRePassword));
    }

    //クリックした時の処理

    public void cancel() {
        mListener.cancelSignUp();
    }

    //このFragmentのリスナー

    public interface OnFragmentInteractionListener {
        void cancelSignUp();
    }
}
