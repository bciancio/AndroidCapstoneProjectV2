package com.bciancio.androidcapstoneprojectv2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bciancio.androidcapstoneprojectv2.entity.Transaction;

import java.util.ArrayList;
/**
 * Created by student on 12/6/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    // TODO change to an Transactions object
    private ArrayList<Transaction> mDataset;
    private Activity mActivity;
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Transaction> myDataset, Activity myActivity) {
        mDataset = myDataset;
        mActivity = myActivity;
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mNameHolder;
        public TextView mTypeHolder;
        public TextView mFgHolder;
        public TextView mIggHolder;
        public RelativeLayout mInfoLayout;

        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.deleteButton);
            mNameHolder = (TextView) v.findViewById(R.id.theName);
            mTypeHolder = (TextView) v.findViewById(R.id.theType);
            mFgHolder = (TextView) v.findViewById(R.id.theFgAmnt);
            mIggHolder = (TextView) v.findViewById(R.id.theIggAmnt);
            mInfoLayout = (RelativeLayout)v.findViewById(R.id.rl_transaction_info);
        }
    }

     // TODO Fix if added this functionality.
//    public void add(int position, Transaction item) {
//        mDataset.add(position, item);
//        notifyItemInserted(position);
//    }

    public void remove(int itemPositon) {

        final int thePosition = itemPositon;

        AlertDialog.Builder alert = new AlertDialog.Builder(mActivity);

        alert.setTitle("Confirm Delete!!");
        alert.setMessage("Are you sure to delete this transaction?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDataset.remove(thePosition);
                // TODO call webservice
                notifyItemRemoved(thePosition);
                dialog.dismiss();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    public void promptUpdateDialog(int itemPosition) {
        // TODO add popup
        logcatThis("a wild popup has appeared.");
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //final int position = mDataset.get(position);
        holder.mNameHolder.setText("Name: " + "anotherVersion");
        holder.mTypeHolder.setText("Type: " + mDataset.get(position).getType());
        holder.mFgHolder.setText("Fg Amnt: " + mDataset.get(position).getFgAmnt());
        holder.mIggHolder.setText("Igg Amnt: " + mDataset.get(position).getIggAmnt());
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logcatThis("mDataSet size  before delete " + getItemCount());
                remove(position);
                logcatThis("mDataSet size after delete " + getItemCount());
            }
        });

        holder.mInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptUpdateDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     * For debuging.
     * @param message what needs to be added to the logcat message
     */
    public void logcatThis(String message) {
        Log.d("MyDebug", "In MyAdapter: " + message);
    }
}
