package com.example.student.productinventory.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.student.productinventory.R;
import com.example.student.productinventory.models.SellHistory;

import java.util.ArrayList;

/**
 * Created by msrabon on 11/30/16.
 */

public class SaleHistoryAdapter extends RecyclerView.Adapter<SaleHistoryAdapter.SalesHistoryViewHolder> {

    private ArrayList<SellHistory> sellHistories;
    private Context context;

    public SaleHistoryAdapter(ArrayList<SellHistory> sellHistories, Context context) {
        this.sellHistories = sellHistories;
        this.context = context;
    }

    @Override
    public SalesHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_sellhistory,parent,false);
        SalesHistoryViewHolder salesHistoryViewHolder = new SalesHistoryViewHolder(view);
        return salesHistoryViewHolder;
    }

    @Override
    public void onBindViewHolder(SalesHistoryViewHolder holder, int position) {
        holder.product_id.setText("PID: " + sellHistories.get(position).getProduct_id());
        holder.product_Name.setText("Name: " + sellHistories.get(position).getProduct_UID());
        holder.product_Stock.setText("Stock: " + sellHistories.get(position).getSell_quantity());
        holder.product_price.setText("Price: " + sellHistories.get(position).getSold_price());
        holder.timeStamp.setText("Time: " + sellHistories.get(position).getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return sellHistories.size();
    }

    public static class SalesHistoryViewHolder extends RecyclerView.ViewHolder {
        CardView cv;

        TextView product_id;
        TextView product_Name;
        TextView product_Stock;
        TextView product_price;
        TextView timeStamp;


        public SalesHistoryViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cardview_sellhistory);
            product_id = (TextView) itemView.findViewById(R.id.product_id);
            product_Name = (TextView) itemView.findViewById(R.id.product_name);
            product_Stock = (TextView) itemView.findViewById(R.id.product_stock);
            product_price = (TextView) itemView.findViewById(R.id.product_price);
            timeStamp = (TextView) itemView.findViewById(R.id.product_category);

        }
    }
}
