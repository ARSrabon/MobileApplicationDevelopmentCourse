package com.example.student.productinventory.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.student.productinventory.R;
import com.example.student.productinventory.activities.ProductDetailsView;
import com.example.student.productinventory.models.Category;
import com.example.student.productinventory.models.Product;

import java.util.ArrayList;

/**
 * Created by student on 10/26/16.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Product> productLists;
    private Context context;


    public ProductAdapter(ArrayList<Product> productLists, Context context) {
        this.productLists = productLists;
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_viewproduct, parent, false);
        ProductViewHolder viewHolder = new ProductViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {

        holder.product_id.setText("Product ID: " + productLists.get(position).getProduct_id());
        holder.product_Name.setText("Name: " + productLists.get(position).getProduct_name());
        holder.product_Stock.setText("Stock: " + productLists.get(position).getProduct_stock());
        holder.product_price.setText("Price: " + productLists.get(position).getProduct_price());
        Category category = productLists.get(position).getCategory();
//        Log.d("Category", category.getCategory_name() );
        holder.product_category.setText("Category: " + category.toString());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = productLists.get(position);
                long product_uid = product.getId();
                Log.d("cardsnotworking", String.valueOf(product.getId().intValue()) );
                Intent intent = new Intent(context, ProductDetailsView.class);
                intent.putExtra("product_id",product_uid);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
//        Log.d("adapter", String.valueOf(productLists.size()));
        return productLists.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cv;

        TextView product_id;
        TextView product_Name;
        TextView product_Stock;
        TextView product_price;
        TextView product_category;


        public ProductViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardview_products);
            product_id = (TextView) itemView.findViewById(R.id.product_id);
            product_Name = (TextView) itemView.findViewById(R.id.product_name);
            product_Stock = (TextView) itemView.findViewById(R.id.product_stock);
            product_price = (TextView) itemView.findViewById(R.id.product_price);
            product_category = (TextView) itemView.findViewById(R.id.product_category);
//              This one worked !!!
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
        }
    }

}
