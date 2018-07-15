package com.dennnoukishidann.workplatform.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dennnoukishidann.workplatform.R;
import com.dennnoukishidann.workplatform.enums.UserExists;
import com.dennnoukishidann.workplatform.firebase.ReadingInFireBase;
import com.dennnoukishidann.workplatform.instanceClass.User;


public class SignInFragment extends Fragment implements View.OnClickListener
        , ReadingInFireBase.OnReturnUserListener, ReadingInFireBase.OnReturnUserExistsListener {


    private OnFragmentInteractionListener mListener;

    View mView;

    EditText mEdMailAddress;
    EditText mEdPassword;

    Button mBtnSignIn;
    Button mBtnSignUp;

    TextInputLayout mTiyMailAddress;
    TextInputLayout mTiyPassword;

    ProgressDialog mProgressDialog;

    boolean mBlEdMailAddress;
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
                    signInFirst();
                    break;
                case R.id.sign_up:
                    signUp();
                    break;
            }
        }
    }

    //FirebaseInOnReturnUserListenerの処理

    @Override
    public void returnUser(User user) {
        signInThird(user);
    }

    //FirebaseInOnReturnUserExistsListenerの処理


    @Override
    public void returnUserExists(UserExists result) {
        switch (result) {
            case EXIST:
                signInSecond();
                break;
            case NON_EXISTS:
                showErrorMessageOnMailAddress();
                break;
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
                    //最初にerrorMessageをなくす
                    mTiyMailAddress.setErrorEnabled(false);
                    mTiyMailAddress.setError(null);

                    if (charSequence.length() != 0) {
                        mBlEdMailAddress = true;
                    } else {
                        mBlEdMailAddress = false;
                    }
                    break;
                case R.id.password:
                    //最初にerrorMessageをなくす
                    mTiyPassword.setErrorEnabled(false);
                    mTiyPassword.setError(null);

                    if (charSequence.length() != 0) {
                        mBlEdPassword = true;
                    } else {
                        mBlEdPassword = false;
                    }
                    break;
            }

            if (mBlEdMailAddress && mBlEdPassword) {
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

        mTiyMailAddress = (TextInputLayout) mView.findViewById(R.id.textInputLayout);
        mTiyPassword = (TextInputLayout) mView.findViewById(R.id.textInputLayout1);
    }

    public void setUpSignUpButton() {
        //SignInのボタンのenableとかをsetUp
        mBtnSignIn.setEnabled(false);
        //ここでfalseにするとselectorが働く
    }

    public void setUpProgressDialog() {
        //progressDialogの処理
        mProgressDialog = new ProgressDialog(getActivity());

        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        String message = getString(R.string.nowLoggingIn);

        mProgressDialog.setMessage(message);

        mProgressDialog.setCancelable(false);

        mProgressDialog.show();
        //activityが変わるときに勝手に消える
    }

    //EditTextに関する処理

    public void showErrorMessageOnMailAddress() {
        //入力したメールアドレスが存在していなかった時
        //ここでボタンの無効化
        mBtnSignIn.setEnabled(false);

        //progressDialogを消す
        mProgressDialog.dismiss();

        String message = getString(R.string.nonExistsMailAddress);

        mTiyMailAddress.setError(message);

        mTiyMailAddress.setErrorEnabled(true);
    }

    public void showErrorMessageOnPassword() {
        //入力したパスワードが正しくなかった時
        mBtnSignIn.setEnabled(false);

        //progressDialogを消す
        mProgressDialog.dismiss();

        String message = getString(R.string.mismatchPassword);

        mTiyPassword.setError(message);

        mTiyPassword.setErrorEnabled(true);
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

    public void signInFirst() {
        //firebaseで指定されたuserを探すところまで
        //まずはprogressDialog
        setUpProgressDialog();

        String mailAddress = mEdMailAddress.getText().toString();

        ReadingInFireBase.OnReturnUserExistsListener listener = (ReadingInFireBase.OnReturnUserExistsListener) this;

        ReadingInFireBase.userWithMailAddressExists(mailAddress, listener);
        //この後継承したreturnUserExistsでそのユーザーが存在するかどうか確認し
        //存在したらsignInSecondに飛ぶ
    }

    public void signInSecond() {
        //userが存在することを確認したのでそこから、実際に取ってくる
        //あっていたらloginFromSignInする
        String mailAddress = mEdMailAddress.getText().toString();

        ReadingInFireBase.OnReturnUserListener listener = (ReadingInFireBase.OnReturnUserListener) this;

        ReadingInFireBase.searchUserByMailAddress(mailAddress, listener);
        //この後実際にuserのinstanceを取得してsignInThirdにいく
    }

    public void signInThird(User user) {
        //実際にloginFromSignInとかする
        //まずはパスワードがあってるかどうか確認
        String password = mEdPassword.getText().toString();

        if (password.equals(user.getPassword())) {
            //実際にLOGIN
            mListener.loginFromSignIn(user.getUserId());
        } else {
            showErrorMessageOnPassword();
        }

    }

    public void signUp() {
        mListener.goSinUp();
    }

    //このfragmentのinterface

    public interface OnFragmentInteractionListener {
        void goSinUp();

        void loginFromSignIn(String userId);
    }
}
