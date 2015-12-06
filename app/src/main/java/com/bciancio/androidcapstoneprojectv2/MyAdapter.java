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
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by student on 12/6/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    // TODO change to an Transactions object
    private ArrayList<String> mDataset;
    private Activity mActivity;
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> myDataset, Activity myActivity) {
        mDataset = myDataset;
        mActivity = myActivity;
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTxtheader;
        public TextView mTxtFooter;
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mTxtheader = (TextView) v.findViewById(R.id.firstLine);
            mTxtFooter = (TextView) v.findViewById(R.id.secondLine);
            mImageView = (ImageView) v.findViewById(R.id.deleteButton);
        }
    }

    public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }


    // TODO prompt user ' are u sure ' dialog
    // TODO this will eventually remove the item from database.
    public void remove(String item) {

        final String theItem = item;

        AlertDialog.Builder alert = new AlertDialog.Builder(mActivity);

        alert.setTitle("Confirm Delete!!");
        alert.setMessage("Are you sure to delete this transaction?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int position = mDataset.indexOf(theItem);
                mDataset.remove(position);
                notifyItemRemoved(position);

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

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = mDataset.get(position);
        holder.mTxtheader.setText(mDataset.get(position));
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logcatThis("mDataSet size  before delete " + getItemCount());
                remove(name);
                logcatThis("mDataSet size after delete " + getItemCount());
            }
        });

        holder.mTxtFooter.setText("Footer: " + mDataset.get(position));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void logcatThis(String message) {
        Log.d("MyDebug", "In MyAdapter: " + message);
    }
}
