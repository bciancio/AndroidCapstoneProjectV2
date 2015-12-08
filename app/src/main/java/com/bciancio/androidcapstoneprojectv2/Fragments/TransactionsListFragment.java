package com.bciancio.androidcapstoneprojectv2.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bciancio.androidcapstoneprojectv2.MyAdapter;
import com.bciancio.androidcapstoneprojectv2.ProjectSettings;
import com.bciancio.androidcapstoneprojectv2.R;
import com.bciancio.androidcapstoneprojectv2.entity.MySingleton;
import com.bciancio.androidcapstoneprojectv2.entity.Transaction;

import java.util.ArrayList;


/**
 * Created by student on 11/17/2015.
 */
public class TransactionsListFragment extends Fragment implements ProjectSettings {
    ArrayList<Transaction> mTransactionArrayList;
    String mUrlBase = "https://damp-mesa-6637.herokuapp.com/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.transactions_list_layout, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeListView();
    }

    public void initializeListView() {

        RecyclerView mRecyclerView = (RecyclerView) getActivity().findViewById(R.id
                .recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        MySingleton mySingleton = MySingleton.get(getActivity());
        mTransactionArrayList = mySingleton.getTransactionArrayList();

        MyAdapter mAdapter = new MyAdapter(mTransactionArrayList, getActivity());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu != null) {

            menu.findItem(R.id.addTransactionMenu).setVisible(true);
        }
    }

    public void logcatThis(String message) {
        Log.d("MyDebug", "In TransactionsList fragment: " + message);
    }
}

