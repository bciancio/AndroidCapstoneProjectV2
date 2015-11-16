package com.bciancio.androidcapstoneprojectv2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner mTransactionType;

    EditText mEtIgg;
    EditText mEtFg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Wire up and add an event click listener to the floating Action button */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logcatThis("the floating action button has been clicked.");
                if (checkIfValidInput()) {
                    addTransaction();
                } else {
                    snackbarThis("Make sure you have value");
                }
            }
        });

        /* Wire up the two editable */
        mEtIgg= (EditText)findViewById(R.id.et_iggAmnt);
        mEtFg = (EditText)findViewById(R.id.et_fgAmnt);
        /* Wire up the spinner, adding the two transaction types */
        mTransactionType = (Spinner) findViewById(R.id.sp_transaction_type);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.transaction_type_array, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mTransactionType.setAdapter(spinnerAdapter);

        /* Wire up the Drawer/Navigation view */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.goToListView:
                logcatThis("Here.");
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        snackbarTransactionAddedSuccess(true);
    }

    public void snackbarTransactionAddedSuccess(boolean success) {
        String message;
        if (success) {
            message = "Transaction added.";
        } else {
            message = "Unable to add Transaction";
        }
        snackbarThis(message);
    }

    public void snackbarThis(String message) {
        View view = findViewById(R.id.coordinator_layout);
        Snackbar
                .make(view, message, Snackbar.LENGTH_LONG)
                .show();
    }

    public void logcatThis(String message) {
        Log.d("MyDebug", "iIn main activity: " + message);
    }
}
