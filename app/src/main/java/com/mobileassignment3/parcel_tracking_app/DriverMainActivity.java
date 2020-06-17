package com.mobileassignment3.parcel_tracking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class DriverMainActivity extends AppCompatActivity {
    private RecyclerView rvMyTask;
    private RecyclerView.Adapter adapterMyTask;
    private RecyclerView.LayoutManager layoutManagerMyTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        getSupportActionBar().setTitle("Driver name");

        rvMyTask = findViewById(R.id.rvMyTask);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rvMyTask.setHasFixedSize(true);

        // use a linear layout manager
        layoutManagerMyTask = new LinearLayoutManager(this);
        rvMyTask.setLayoutManager(layoutManagerMyTask);

        // specify an adapter
        MyTask[] myDataset = new MyTask[] {
                new MyTask("Parcel One", "Beef inside"),
                new MyTask("Parcel Two", "Countdown delivery"),
                new MyTask("Parcel Three", "Dahua supermarket tuan gou"),
                new MyTask("Parcel 4", "Beef inside"),
                new MyTask("Parcel 5", "Countdown delivery"),
                new MyTask("Parcel 6", "Dahua supermarket tuan gou"),
                new MyTask("Parcel 7", "Beef inside"),
                new MyTask("Parcel 8", "Countdown delivery"),
                new MyTask("Parcel 9", "Dahua supermarket tuan gou"),
        };
        adapterMyTask = new MyAdapter(myDataset);
        rvMyTask.setAdapter(adapterMyTask);
    }
}

class MyTask {
    public final String title;
    public final String detail;

    MyTask(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }
}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private MyTask[] mDataset;

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
    public MyAdapter(MyTask[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_my_task, parent, false);
        TextView title = (TextView) v.findViewById(R.id.cardMyTaskTitle);
        TextView detail = (TextView) v.findViewById(R.id.cardMyTaskDetail);

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
