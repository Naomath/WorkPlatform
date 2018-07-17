package com.dennnoukishidann.workplatform.workFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dennnoukishidann.workplatform.R;

public class SearchWorksFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    View mView;

    public SearchWorksFragment() {
        // Required empty public constructor
    }

    public static SearchWorksFragment newInstance() {
        SearchWorksFragment fragment = new SearchWorksFragment();
        return fragment;
    }

    //Fragmentのライフサイクルの処理

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
        mListener = null;
    }

    //Viewたちの設定

    public void setUpViews(LayoutInflater inflater, ViewGroup container){
        mView =  inflater.inflate(R.layout.fragment_search_works, container, false);
    }

    //このフラグメントのリスナー

    public interface OnFragmentInteractionListener {
    }
}
