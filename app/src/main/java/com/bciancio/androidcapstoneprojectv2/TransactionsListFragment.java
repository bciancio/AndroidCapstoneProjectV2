package com.bciancio.androidcapstoneprojectv2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bciancio.androidcapstoneprojectv2.entity.PurchasedTransaction;
import com.bciancio.androidcapstoneprojectv2.entity.SoldTransaction;
import com.bciancio.androidcapstoneprojectv2.entity.Transactions;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.util.ArrayList;


/**
 * Created by student on 11/17/2015.
 */
public class TransactionsListFragment extends Fragment {
    Transactions transactions;


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
}
