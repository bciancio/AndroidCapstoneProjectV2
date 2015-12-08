package com.bciancio.androidcapstoneprojectv2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProjectSettings{



    Spinner mTransactionType;

    EditText mEtIgg;
    EditText mEtFg;

    /**
     * In the oncreate I call wireupWidgets to wire up the necessary widgets.
     * If savedInstanceState != null than create a fragment to apply to the layout.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logcatThis("onCreate() has been called.");

        wireUpWidgets();
        populateTheData();
        // Check that the activity is using the layout version with
        // the id:fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }


            // Create a new Fragment to be placed in the activity layout
            AddTransactionsFragment fragment = new AddTransactionsFragment();
            replaceFragment(fragment);

            setTitle(R.string.fragment_title_add);
        }

    }

    public void populateTheData() {
        logcatThis("about to populate the data.");
        AsyncTaskClient populateDataTask = new AsyncTaskClient(this);
        populateDataTask.execute(TRANSACTION_DRIVER +  "/" + GET_ALL_TRANSACTIONS);
    }

    /**
     * Wire up the widgets:
     * -toolbar
     * -drawer
     * -navigation view
     */
    public void wireUpWidgets() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         /* Wire up the Drawer/Navigation view */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Override method to close the drawer if its open or call the super onBackPressed.
     */
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

    /**
     * Inflates the menu_main layout to use.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Logic for each item, for when it is selected
     * @param item
     * @return
     */
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

    /**
     * This is the onSelectedListeners for each MenuItem in the navigation drawer
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.goToListView:
                logcatThis("Go to List View clicked.");

                TransactionsListFragment transactionsListFragment= new TransactionsListFragment();
                replaceFragment(transactionsListFragment);

                setTitle(R.string.fragment_title_list);
                break;

            case R.id.goToAddTransaction:
                logcatThis("Go to add transaction clicked.");

                AddTransactionsFragment addTransactionsFragment = new AddTransactionsFragment();
                replaceFragment(addTransactionsFragment);

                setTitle(R.string.fragment_title_add);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This method is in charge of replacing a Fragment with another fragment.
     * @param fragment the new fragment
     */
    public void replaceFragment(Fragment fragment) {
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();


    }

    /**
     * This creates a snackBarMessage
     * @param message the message to be snackbared
     */
    public void snackbarThis(String message) {
        View view = findViewById(R.id.coordinator_layout);
        Snackbar
                .make(view, message, Snackbar.LENGTH_LONG)
                .show();
    }

    /**
     * For debuging.
     * @param message what needs to be added to the logcat message
     */
    public void logcatThis(String message) {
        Log.d("MyDebug", "In main activity: " + message);
    }


    private class AsyncCaller extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
