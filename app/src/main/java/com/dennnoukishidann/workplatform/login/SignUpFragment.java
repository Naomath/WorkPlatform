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
import android.widget.ImageButton;

import com.dennnoukishidann.workplatform.R;
import com.dennnoukishidann.workplatform.enums.UserExists;
import com.dennnoukishidann.workplatform.firebase.ReadingInFireBase;
import com.dennnoukishidann.workplatform.firebase.WritingInFireBase;
import com.dennnoukishidann.workplatform.instanceClass.User;

public class SignUpFragment extends Fragment implements View.OnClickListener
        , WritingInFireBase.OnCompleteListener, ReadingInFireBase.OnReturnUserExistsListener {


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

    TextInputLayout mTiyMailAddress;
    TextInputLayout mTiyPassword;
    TextInputLayout mTiyRePassword;

    ProgressDialog mProgressDialog;
    //signUpの時の処理中に使う

    public SignUpFragment() {
    }

    public static SignUpFragment newInstance() {
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
                    done();
                    break;
                case R.id.cancel:
                    cancel();
                    break;

            }
        }
    }

    //WritingInFireBase.OnCompleteListenerのメソッド

    @Override
    public void completeFirebaseProcessing(Bundle bundle) {
        String userId = bundle.getString(String.valueOf(R.string.BundleUserIdKey));

        mListener.loginFromSignUp(userId);
    }

    //ReadingInFireBase.OnReturnUserExistsListenerのメソッド

    @Override
    public void returnUserExists(UserExists result) {
        switch (result) {
            case EXIST:
                //すでに存在していた場合
                showErrorMessageOnMailAddress();
                break;
            case NON_EXISTS:
                //存在していなかった場合
                signUpUserSecond();
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
                case R.id.user_name:
                    if (charSequence.length() != 0) {
                        mBlEdUserName = true;
                    } else {
                        mBlEdUserName = false;
                    }
                    break;

                case R.id.mail_address:
                    mTiyMailAddress.setErrorEnabled(false);
                    mTiyMailAddress.setError(null);

                    if (charSequence.length() != 0) {
                        mBlEdMailAddress = true;
                    } else {
                        mBlEdMailAddress = false;
                    }
                    break;

                case R.id.password:
                    if (charSequence.length() != 0) {
                        mBlEdPassword = true;

                        mTiyPassword.setErrorEnabled(false);
                        mTiyPassword.setError(null);
                        mTiyRePassword.setErrorEnabled(false);
                        mTiyRePassword.setError(null);
                    } else {
                        mBlEdPassword = false;
                    }
                    break;

                case R.id.re_password:
                    if (charSequence.length() != 0) {
                        mBlEdRePassword = true;

                        mTiyPassword.setErrorEnabled(false);
                        mTiyPassword.setError(null);
                        mTiyRePassword.setErrorEnabled(false);
                        mTiyRePassword.setError(null);
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

        setUpDoneButton();

        mEdUserName = (EditText) mView.findViewById(R.id.user_name);
        mEdMailAddress = (EditText) mView.findViewById(R.id.mail_address);
        mEdPassword = (EditText) mView.findViewById(R.id.password);
        mEdRePassword = (EditText) mView.findViewById(R.id.re_password);

        mTiyMailAddress = (TextInputLayout) mView.findViewById(R.id.textInputLayout1);
        mTiyPassword = (TextInputLayout) mView.findViewById(R.id.textInputLayout2);
        mTiyRePassword = (TextInputLayout) mView.findViewById(R.id.textInputLayout3);
    }

    public void setUpDoneButton() {
        //SignInのボタンのenableとかをsetUp
        mBtnDone.setEnabled(false);
        //ここでfalseにするとselectorが働く
    }

    public void setUpProgressDialog() {
        //progressdialogの処理
        mProgressDialog = new ProgressDialog(getActivity());

        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        String message = getString(R.string.nowLoggingIn);

        mProgressDialog.setMessage(message);

        mProgressDialog.setCancelable(false);

        mProgressDialog.show();
        //activityが変わるときに勝手に消える
    }

    //EditTextに関する処理

    public void showErrorMessageOnPassword() {
        //最初のパスワードと次のパスワードが一致しなかった時に出す処理
        //ここでボタンの無効化をする
        mBtnDone.setEnabled(false);

        String message = getString(R.string.mismatchTwoPasswords);

        mTiyPassword.setError(message);
        mTiyRePassword.setError(message);

        mTiyPassword.setErrorEnabled(true);
        mTiyRePassword.setErrorEnabled(true);
    }

    public void showErrorMessageOnMailAddress() {
        //メールアドレスがすでに登録さていた時に出す処理
        //ここでボタンの無効化
        mBtnDone.setEnabled(false);

        //まずはprogressDialogを消す
        mProgressDialog.dismiss();

        String message = getString(R.string.existsMailAddressAlready);

        mTiyMailAddress.setError(message);

        mTiyMailAddress.setErrorEnabled(true);

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
        //キャンセルボタンを押した時の処理
        mListener.cancelSignUp();
    }

    public void done() {
        //DONEボタンを押した時の処理

        //まずはパスワードの最初に入れたやつと確認ようが一致しているか確かめる
        String password = mEdPassword.getText().toString();
        String rePassword = mEdRePassword.getText().toString();

        if (password.equals(rePassword)) {
            //一致した時
            sinUpUserFirst();
        } else {
            //不一致だった時
            //エラーメッセージを出す
            showErrorMessageOnPassword();
        }
    }

    //ユーザーを登録する処理

    public void sinUpUserFirst() {
        //ユーザーを登録する処理の第一段階
        //mailAddressの先に登録されているかどうかを確認する前まで
        //先にボタンを押せないようにする
        mBtnDone.setEnabled(false);

        //次にprogressdialogを起動させる
        setUpProgressDialog();

        String mailAddress = mEdMailAddress.getText().toString();

        ReadingInFireBase.OnReturnUserExistsListener listener = (ReadingInFireBase.OnReturnUserExistsListener) this;
        //このメソッドで確認する
        ReadingInFireBase.userWithMailAddressExists(mailAddress, listener);

    }

    //ここで指定したメールアドレスが使われているか確認する（returnUserExists()メソッド）

    public void signUpUserSecond() {
        //returnUserExists()メソッドから呼ばれる
        //実際に登録していく
        String name = mEdUserName.getText().toString();
        String mailAddress = mEdMailAddress.getText().toString();
        String password = mEdPassword.getText().toString();

        User user = new User(name, mailAddress, password);

        WritingInFireBase.OnCompleteListener listener = (WritingInFireBase.OnCompleteListener) this;

        WritingInFireBase.addUser(user, listener);
    }

    //このFragmentのリスナー

    public interface OnFragmentInteractionListener {
        void cancelSignUp();

        void loginFromSignUp(String userId);
    }
}
