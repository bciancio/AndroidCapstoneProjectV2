package com.bciancio.androidcapstoneprojectv2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;


/**
 * Created by student on 11/17/2015.
 */
public class TransactionsListFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        wireUpWidgets();
        populateData();
    }

    public  void wireUpWidgets() {

        // Perform magic and make the fabulous floating action bar disappear!
        FloatingActionButton fab = (FloatingActionButton)getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
    }

    public void populateData() {
        RestClientUsage rcu = new RestClientUsage();
        rcu.getTest();
    }

    public void invokeWS( ) {

    }
}
