package com.dennnoukishidann.workplatform.workFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dennnoukishidann.workplatform.R;

public class RecruitWorkFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    View mView;

    public RecruitWorkFragment() {
        // Required empty public constructor
    }

    public static RecruitWorkFragment newInstance() {
        RecruitWorkFragment fragment = new RecruitWorkFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //Fragmentのサイクル

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpViews(inflater, container);
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //Viewたちのセットアップ

    public void setUpViews(LayoutInflater inflater, ViewGroup container){
        mView = inflater.inflate(R.layout.fragment_recruit_work, container, false);
    }

    //Fragmentのリスナー

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
