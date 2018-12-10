package com.example.brioz.siwakaadmin.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brioz.siwakaadmin.Common.Common;
import com.example.brioz.siwakaadmin.Interface.ItemClickListener;
import com.example.brioz.siwakaadmin.R;


public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener {
    public TextView menu_name;
    public ImageView menu_image;

    private ItemClickListener itemClicklistener;



    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        menu_name=(TextView)itemView.findViewById(R.id.menu_name);
        menu_image =(ImageView)itemView.findViewById(R.id.menu_image);


        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);

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
        menu.setHeaderTitle("Select Action");

        menu.add(0,0, getAdapterPosition(),Common.UPDATE);
        menu.add(0,1, getAdapterPosition(),Common.DELETE);
    }
}
