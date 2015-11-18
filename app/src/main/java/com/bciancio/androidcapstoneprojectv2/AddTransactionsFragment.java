package com.bciancio.androidcapstoneprojectv2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
/**
 * Created by student on 11/17/2015.
 */
public class AddTransactionsFragment  extends Fragment {

    Spinner mTransactionType;
    EditText mEtIgg;
    EditText mEtFg;

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
//      return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        wireUpWidgets();
    }

    public void wireUpWidgets() {

        /* Wire up the two editable */
        mEtIgg= (EditText)getActivity().findViewById(R.id.et_iggAmnt);
        mEtFg = (EditText)getActivity().findViewById(R.id.et_fgAmnt);

        /* Wire up the spinner, adding the two transaction types */
        mTransactionType = (Spinner)getActivity().findViewById(R.id.sp_transaction_type);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter
                .createFromResource(getActivity(),R.array.transaction_type_array,
                                    android.R.layout.simple_spinner_item
                                    );

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mTransactionType.setAdapter(spinnerAdapter);

        /* Wire up and add an event click listener to the floating Action button */
        FloatingActionButton fab = (FloatingActionButton)getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logcatThis("the floating action button has been clicked.");
//                if (checkIfValidInput()) {
//                    addTransaction();
                // clearTheEditFields
//                }
//                else {
//                    snackbarThis("Make sure you have valid values");
//                }
//            }
            }
        });
    }
    public boolean checkIfValidInput() {
        if(mEtFg.getText().length() != 0 && mEtFg.getText().length() != 0) {
            return true;
        }
        return false;
    }

    public void addTransaction(){

        String typeValue = mTransactionType.getSelectedItem().toString();
        int iggValue = Integer.parseInt(mEtIgg.getText().toString());
        int fgValue = Integer.parseInt(mEtFg.getText().toString());

        // TODO
        // connect to webservice get the reply.
        // pass if added below instead of hard value
        // snackbarTransactionAddedSuccess(true);
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
