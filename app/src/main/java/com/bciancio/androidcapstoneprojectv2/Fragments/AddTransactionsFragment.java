package com.bciancio.androidcapstoneprojectv2.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bciancio.androidcapstoneprojectv2.MyTaskHandler;
import com.bciancio.androidcapstoneprojectv2.ProjectSettings;
import com.bciancio.androidcapstoneprojectv2.R;
import com.bciancio.androidcapstoneprojectv2.entity.MySingleton;
import com.bciancio.androidcapstoneprojectv2.entity.Transaction;
/**
 * Created by student on 11/17/2015.
 */
public class AddTransactionsFragment  extends Fragment implements ProjectSettings {

    Spinner mTransactionType;
    EditText mEtFg;
    EditText mEtIgg;
    Button mAddButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_transaction_layout, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        wireUpWidgets();
    }

    public void wireUpWidgets() {

        //  Wire up the two editable
        mEtFg = (EditText)getActivity().findViewById(R.id.et_fgAmnt);
        mEtIgg= (EditText)getActivity().findViewById(R.id.et_iggAmnt);

        // Wire up the spinner, adding the two transaction types
        mTransactionType = (Spinner)getActivity().findViewById(R.id.sp_transaction_type);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter
                .createFromResource(getActivity(),R.array.transaction_type_array,
                                    android.R.layout.simple_spinner_item
                                    );

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mTransactionType.setAdapter(spinnerAdapter);

        mAddButton = (Button)getActivity().findViewById(R.id.btn_add_transaction);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIfValidInput()) {
                    logcatThis("valid user input");
                    addTransaction();
                }
                else {
                    logcatThis("invalid user input");
                    snackbarThis("Invalid transaction.");
                }
            }
        });
    }
    public boolean checkIfValidInput() {
        if(mEtFg.getText().length() != 0 && mEtIgg.getText().length() != 0) {
            return true;
        }
        return false;
    }

    public void addTransaction(){

        String typeValue = mTransactionType.getSelectedItem().toString();
        int fgValue = Integer.parseInt(mEtFg.getText().toString());
        int iggValue = Integer.parseInt(mEtIgg.getText().toString());

        MySingleton mySingleton = MySingleton.get(getActivity());
        Transaction newTransaction = new Transaction(typeValue,fgValue,iggValue);

        int sizeBefore = mySingleton.getTransactionArrayListSize();
        logcatThis("sizeBefore: " + sizeBefore);
        int sizeAfter;

        logcatThis("calling the Async to add transaction.");
        MyTaskHandler addTransactionTask = new MyTaskHandler(getActivity(), newTransaction);


        addTransactionTask.execute(TRANSACTION_DRIVER + "/" + ADD_TRANSACTION);
        try {
            Thread.sleep(850);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sizeAfter = mySingleton.getTransactionArrayListSize();

        logcatThis("sizeAfter : " + sizeAfter );

        if(sizeBefore < sizeAfter) {
            snackbarThis("Transaction successfully added.");
            clearInputFields();
        } else {
            snackbarThis("Unable to add transaction.");
        }

    }

    private void clearInputFields() {
        mTransactionType.setSelection(0);
        mEtFg.setText("");
        mEtIgg.setText("");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu != null) {

            menu.findItem(R.id.addTransactionMenu).setVisible(false);
        }
    }

    public void snackbarThis(String message) {
        View view = getActivity().findViewById(R.id.coordinator_layout);
        Snackbar
                .make(view, message, Snackbar.LENGTH_LONG)
                .show();
    }

    public void logcatThis(String message) {
        Log.d("MyDebug", "In AddTransaction fragment: " + message);
    }
}
