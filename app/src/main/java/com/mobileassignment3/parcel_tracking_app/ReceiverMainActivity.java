package com.mobileassignment3.parcel_tracking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReceiverMainActivity extends AppCompatActivity {
    private RecyclerView rvMyParcels;
    private RecyclerView.Adapter adapterMyParcels;
    private RecyclerView.LayoutManager layoutManagerMyParcels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_main);

        getSupportActionBar().setTitle("Receiver name");

        rvMyParcels = findViewById(R.id.rvMyParcels);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rvMyParcels.setHasFixedSize(true);

        // use a linear layout manager
        layoutManagerMyParcels = new LinearLayoutManager(this);
        rvMyParcels.setLayoutManager(layoutManagerMyParcels);

        // specify an adapter
        MyParcels[] myDataset = new MyParcels[] {
                new MyParcels("Parcel 1", "Beef inside"),
                new MyParcels("Parcel 2", "Countdown delivery"),
                new MyParcels("Parcel 3", "Dahua supermarket tuan gou"),
                new MyParcels("Parcel 4", "Beef inside"),
                new MyParcels("Parcel 5", "Countdown delivery"),
                new MyParcels("Parcel 6", "Dahua supermarket tuan gou"),
                new MyParcels("Parcel 7", "Beef inside"),
                new MyParcels("Parcel 8", "Countdown delivery"),
                new MyParcels("Parcel 9", "Dahua supermarket tuan gou"),
        };
        adapterMyParcels = new ParcelAdapter(myDataset);
        rvMyParcels.setAdapter(adapterMyParcels);
    }
}

class MyParcels {
    public final String title;
    public final String detail;

    MyParcels(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }
}

class ParcelAdapter extends RecyclerView.Adapter<ParcelAdapter.MyViewHolder> {
    private MyParcels[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public TextView textViewTitle;
        public TextView textViewDetail;

        public MyViewHolder(CardView v, TextView tv1, TextView tv2) {
            super(v);
            cardView = v;
            textViewTitle = tv1;
            textViewDetail = tv2;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ParcelAdapter(MyParcels[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ParcelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_my_parcel, parent, false);
        TextView title = (TextView) v.findViewById(R.id.cardMyParcelTitle);
        TextView detail = (TextView) v.findViewById(R.id.cardMyParcelDetail);

        MyViewHolder vh = new MyViewHolder(v, title, detail);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textViewTitle.setText(mDataset[position].title);
        holder.textViewDetail.setText(mDataset[position].detail);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
