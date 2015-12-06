package com.bciancio.androidcapstoneprojectv2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bciancio.androidcapstoneprojectv2.entity.PurchasedTransaction;
import com.bciancio.androidcapstoneprojectv2.entity.SoldTransaction;
import com.bciancio.androidcapstoneprojectv2.entity.Transactions;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by student on 11/17/2015.
 */
public class TransactionsListFragment extends Fragment {
    Transactions transactions;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wireUpWidgets();
        populateData();
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

        RecyclerView mRecyclerView = (RecyclerView)getActivity().findViewById(R.id
                .recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<String> myDataset = new ArrayList<>();
        myDataset.add("one");
        myDataset.add("two");
        myDataset.add("three");
        myDataset.add("four");
        myDataset.add("five");
        myDataset.add("six");
        myDataset.add("seven");
        myDataset.add("eight");


        // TODO set myDataset == Transactions object - modify MyAdapter after
        // specify an adapter (see also next example)
        MyAdapter mAdapter = new MyAdapter(myDataset, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    public  void wireUpWidgets() {
        // Perform magic and make the fabulous floating action bar disappear!
        FloatingActionButton fab = (FloatingActionButton)getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
    }

    public void populateData() {

//        SimpleUnmarshaller simpleUnmarshaller = new SimpleUnmarshaller();
//        File xmlFile = new File("allTransactions.xml");
//        transactions = simpleUnmarshaller.unmarshallFile(xmlFile);
//
//        logcatThis(transactions.toString());

//        RestClientUsage rcu = new RestClientUsage();
//        rcu.getTest();

        // TODO switch out this hardcoded with a JSON mapper from client request.
        transactions = new Transactions();
        PurchasedTransaction purchasedTransaction = new PurchasedTransaction(200, 200);
        SoldTransaction soldTransaction = new SoldTransaction(100, 100);

        ArrayList<PurchasedTransaction> alpt = new ArrayList<>();
        alpt.add(purchasedTransaction);

        ArrayList<SoldTransaction> alst = new ArrayList<>();
        alst.add(soldTransaction);

        transactions.setPurchasedTransactions(alpt);
        transactions.setSoldTransactions(alst);
    }

    public void invokeWS( ) {

    }

    public void logcatThis(String message) {
        Log.d("MyDebug", "In TransactionsList fragment: " + message);
    }
}
