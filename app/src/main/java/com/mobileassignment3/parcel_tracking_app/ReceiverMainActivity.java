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
    private RecyclerView rvMyParcel;
    private RecyclerView.Adapter adapterMyParcel;
    private RecyclerView.LayoutManager layoutManagerMyParcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_main);

        // Change the actionbar title and icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_person_pin_black_24dp);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Receiver name");

        rvMyParcel = findViewById(R.id.rvMyParcel);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rvMyParcel.setHasFixedSize(true);

        // use a linear layout manager
        layoutManagerMyParcel = new LinearLayoutManager(this);
        rvMyParcel.setLayoutManager(layoutManagerMyParcel);

        // specify an adapter
        MyParcel[] myDataset = new MyParcel[] {
                new MyParcel("Parcel 1", "Beef inside"),
                new MyParcel("Parcel 2", "Countdown delivery"),
                new MyParcel("Parcel 3", "Dahua supermarket tuan gou"),
                new MyParcel("Parcel 4", "Beef inside"),
                new MyParcel("Parcel 5", "Countdown delivery"),
                new MyParcel("Parcel 6", "Dahua supermarket tuan gou"),
                new MyParcel("Parcel 7", "Beef inside"),
                new MyParcel("Parcel 8", "Countdown delivery"),
                new MyParcel("Parcel 9", "Dahua supermarket tuan gou"),
        };
        adapterMyParcel = new ParcelAdapter(myDataset);
        rvMyParcel.setAdapter(adapterMyParcel);
    }
}

class MyParcel {
    public final String title;
    public final String detail;

    MyParcel(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }
}

class ParcelAdapter extends RecyclerView.Adapter<ParcelAdapter.MyViewHolder> {
    private MyParcel[] mDataset;

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
    public ParcelAdapter(MyParcel[] myDataset) {
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
