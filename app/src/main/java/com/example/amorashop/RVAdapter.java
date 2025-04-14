package com.example.amorashop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

// 1. Define your data model (the type of data each item in the list represents)
class MyDataItem {
    private final String numOfItem;
    private final String itemPrice;
    private final String itemImage;

    public MyDataItem(String numOfItem, String itemPrice, String itemImage) {
        this.numOfItem = numOfItem;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public String getNumOfItem() {
        return numOfItem;
    }
    public String getItemPrice() { return itemPrice; }
    public String getItemImage() { return itemImage; }
}

// 2. Create a ViewHolder class (represents a single item view)
class MyViewHolder extends RecyclerView.ViewHolder {
    private final TextView numOfItemTextView;
    private final TextView itemPriceTextView;
    private final ImageView imageView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        numOfItemTextView = itemView.findViewById(R.id.tvNumOfItem); // Replace with your actual IDs
        itemPriceTextView = itemView.findViewById(R.id.tvPriceItem); // Replace with your actual IDs
        imageView = itemView.findViewById(R.id.ivItemImage); // Replace with your actual IDs
    }

    public void bind(MyDataItem item) {
        numOfItemTextView.setText(item.getNumOfItem());
        itemPriceTextView.setText(item.getItemPrice());
        Glide.with(itemView.getContext())
                .load(item.getItemImage())
                .into(imageView);
    }
}

// 3. RecyclerView Adapter
public class RVAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private final List<MyDataItem> data;
    private final Context context;

    public RVAdapter(Context context, List<MyDataItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.in_game_currency_layout, parent, false); // Replace with your item layout
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyDataItem item = data.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}