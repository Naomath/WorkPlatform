package com.dennnoukishidann.workplatform.chatFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dennnoukishidann.workplatform.R;

public class ChatToEmployerFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    View mView;

    public ChatToEmployerFragment() {
        // Required empty public constructor
    }

    public static ChatToEmployerFragment newInstance() {
        ChatToEmployerFragment fragment = new ChatToEmployerFragment();
        return fragment;
    }

    //フラグメントのライフサイクル

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpViews(inflater, container);
        //TODO:write
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

    //Viewたちのセットアップ

    public void setUpViews(LayoutInflater inflater, ViewGroup container){
        mView = inflater.inflate(R.layout.fragment_chat_to_employer, container, false);
    }

    //このフラグメントのリスナー

    public interface OnFragmentInteractionListener {
    }
}
