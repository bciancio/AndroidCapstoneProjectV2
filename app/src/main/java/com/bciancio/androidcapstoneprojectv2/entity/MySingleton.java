package com.bciancio.androidcapstoneprojectv2.entity;

import android.content.Context;

import java.util.ArrayList;
/**
 * Created by student on 12/8/2015.
 */
public class MySingleton {
    private static MySingleton sTransactionSingleton;
    // allows singleton to start activities, access project resources,
    // find your application private storage, and much more
    private Context mAppContext;

    private ArrayList<Transaction> mTransactionArrayList;

    private MySingleton(Context appContext) {
        mTransactionArrayList = new ArrayList<>();
    }

    /**
     * Get mySingleton.
     *
     * @param context the context
     * @return the transactions singleton
     */
    public static MySingleton get(Context context) {
        if(sTransactionSingleton == null){
            //create the singleton using the context that is global
            sTransactionSingleton = new MySingleton(context.getApplicationContext());
        }
        return sTransactionSingleton;
    }

    /**
     * Sets transaction array list.
     *
     * @param transactionArrayList the transaction array list
     */
    public void setTransactionArrayList(ArrayList<Transaction> transactionArrayList) {
        mTransactionArrayList = transactionArrayList;
    }

    /**
     * Get transaction array list.
     *
     * @return the array list
     */
    public ArrayList<Transaction> getTransactionArrayList(){
        return mTransactionArrayList;
    }

    /**
     * Add transaction to singleton array list.
     *
     * @param transaction the transaction
     */
    public void addTransactionToSingletonArrayList(Transaction transaction) {
        getTransactionArrayList().add(transaction);
    }

    /**
     * Replace transaction in singleton array list.
     *
     * @param index the index
     * @param transaction the transaction
     */
    public void replaceTransactionInSingletonArrayList(int index, Transaction transaction) {
        getTransactionArrayList().set(index, transaction);
    }

    /**
     * Gets transaction array list size.
     *
     * @return the transaction array list size
     */
    public int getTransactionArrayListSize() {
        return mTransactionArrayList.size();
    }
}
