package com.example.brioz.siwakaadmin;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.brioz.siwakaadmin.Common.Common;
import com.example.brioz.siwakaadmin.Interface.ItemClickListener;
import com.example.brioz.siwakaadmin.Model.Request;
import com.example.brioz.siwakaadmin.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class OrderStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request,OrderViewHolder> adapter;

    MaterialSpinner spinner;
    FirebaseDatabase database;
    DatabaseReference requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        database=FirebaseDatabase.getInstance();
        requests= database.getReference("Requests");

        recyclerView=(RecyclerView)findViewById(R.id.listorders);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders();
    }

    private void loadOrders() {
        adapter=new FirebaseRecyclerAdapter<Request, OrderViewHolder>(

                Request.class,
                R.layout.order,
                OrderViewHolder.class,
                requests) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
                viewHolder.txtorder_id.setText(adapter.getRef(position).getKey());
                viewHolder.txtorder_status.setText(Common.convertCodeToStatus(model.getStatus()));
                viewHolder.txttotalprice.setText(model.getTotal());
                viewHolder.txtorder_phone.setText(model.getPhone());

                viewHolder.setItemClicklistener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });

            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
 
        if(item.getIntent().equals(Common.UPDATE))
            showUpdateDialog(adapter.getRef(item.getOrder()).getKey(),adapter.getItem(item.getOrder()));
        else if(item.getIntent().equals(Common.DELETE))
            deleteOrder(adapter.getRef(item.getOrder()).getKey());
            return super.onContextItemSelected(item);    
    }

    private void showUpdateDialog(String key, final Request item) {
        final AlertDialog.Builder alertDialog =new AlertDialog.Builder(OrderStatus.this);
        alertDialog.setTitle("Update Order ");
        alertDialog.setMessage("Choose The Current Status");
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.update_order_layout,null);

        spinner =(MaterialSpinner)view.findViewById(R.id.statusSpinner);
        spinner.setItems("Order Placed","Order is being Prepared","Order was picked up");

        alertDialog.setView(view);

        final String localKey =key;
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                item.setStatus(String.valueOf(spinner.getSelectedIndex()));

                requests.child(localKey).setValue(item);
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }


    private void deleteOrder(String key) {
        requests.child(key).removeValue();
    }
}
