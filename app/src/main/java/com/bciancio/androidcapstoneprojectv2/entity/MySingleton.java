package com.bciancio.androidcapstoneprojectv2.entity;

import android.content.Context;

import java.util.ArrayList;
/**
 * Created by student on 12/8/2015.
 */
public class MySingleton {
    private static MySingleton sContactSingleton;
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
     * @return the contacts singleton
     */
    public static MySingleton get(Context context) {
        if(sContactSingleton == null){
            //create the singleton using the context that is global
            sContactSingleton = new MySingleton(context.getApplicationContext());
        }
        return sContactSingleton;
    }

    /**
     * Sets contact array list.
     *
     * @param transactionArrayList the contact array list
     */
    public void setTransactionArrayList(ArrayList<Transaction> transactionArrayList) {
        mTransactionArrayList = transactionArrayList;
    }

    /**
     * Get contact array list.
     *
     * @return the array list
     */
    public ArrayList<Transaction> getTransactionArrayList(){
        return mTransactionArrayList;
    }

    /**
     * Add contact to singleton array list.
     *
     * @param transaction the contact
     */
    public void addContactToSingletonArrayList(Transaction transaction) {
        getTransactionArrayList().add(transaction);
    }

    /**
     * Replace contact in singleton array list.
     *
     * @param index the index
     * @param transaction the contact
     */
    public void replaceContactInSingletonArrayList(int index, Transaction transaction) {
        getTransactionArrayList().set(index, transaction);
    }

    /**
     * Gets contact array list size.
     *
     * @return the contact array list size
     */
    public int getContactArrayListSize() {
        return mTransactionArrayList.size();
    }
}
