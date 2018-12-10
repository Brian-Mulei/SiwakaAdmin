package com.example.brioz.siwakaadmin.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.brioz.siwakaadmin.Interface.ItemClickListener;
import com.example.brioz.siwakaadmin.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener{

    public TextView txttotalprice,txtorder_phone,txtorder_status,txtorder_id;

    private ItemClickListener itemClicklistener;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        txttotalprice =(TextView)itemView.findViewById(R.id.totalprice);
        txtorder_phone =(TextView)itemView.findViewById(R.id.order_phone);
        txtorder_status =(TextView)itemView.findViewById(R.id.order_status);
        txtorder_id =(TextView)itemView.findViewById(R.id.order_id);

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);


    }

    public void setItemClicklistener(ItemClickListener itemClicklistener) {
        this.itemClicklistener = itemClicklistener;
    }

    @Override
    public void onClick(View v) {
        itemClicklistener.onClick(v,getAdapterPosition(),false);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select The Action");

            menu.add(0,0,getAdapterPosition(),"Update");
        menu.add(0,1,getAdapterPosition(),"Update");
        menu.add(0,2,getAdapterPosition(),"Update");
    }
}
