package com.example.msrabon.productinventory.adapters;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.msrabon.productinventory.R;
import com.example.msrabon.productinventory.models.Product;

import java.util.ArrayList;

/**
 * Created by student on 10/26/16.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Product> productLists;

    public ProductAdapter(ArrayList<Product> productLists) {
        this.productLists = productLists;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        ProductViewHolder viewHolder = new ProductViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.product_id.setText("PID: " + productLists.get(position).getProduct_id());
        holder.product_Name.setText("Name: " + productLists.get(position).getProduct_name());
        holder.product_Stock.setText("Stock: " + productLists.get(position).getProduct_stock());
        holder.product_price.setText("Price: " + productLists.get(position).getProduct_price());
        Log.d("Product_Adapter", productLists.get(position).getProduct_name());
    }

    @Override
    public int getItemCount() {
        Log.d("adapter", String.valueOf(productLists.size()));
        return productLists.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cv;

        TextView product_id;
        TextView product_Name;
        TextView product_Stock;
        TextView product_price;


        public ProductViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.recyle_view);
            product_id = (TextView) itemView.findViewById(R.id.product_id);
            product_Name = (TextView) itemView.findViewById(R.id.product_name);
            product_Stock = (TextView) itemView.findViewById(R.id.product_stock);
            product_price = (TextView) itemView.findViewById(R.id.product_price);
        }
    }

}
