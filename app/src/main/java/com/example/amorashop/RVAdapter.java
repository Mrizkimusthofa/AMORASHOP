package com.example.amorashop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.math.BigDecimal;
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

    public void bind(MyDataItem item, RVAdapter.OnItemCLickListener listener, boolean isSelected) {
        numOfItemTextView.setText(item.getNumOfItem());
        itemPriceTextView.setText(item.getItemPrice());
        Glide.with(itemView.getContext())
                .load(item.getItemImage())
                .into(imageView);

        // Set background based on selection state
        if (isSelected) {
            itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.color.splash_bg));
            int pay_price = Integer.parseInt(item.getItemPrice().replace("Rp", "").replace(".", ""));
            long pay_tax = Math.round(pay_price * 0.11);
            long pay_total = pay_price + pay_tax;

            Funcs.item_info = item.getNumOfItem();
            Funcs.pay_price = String.valueOf(pay_price);
            Funcs.pay_tax = String.valueOf(pay_tax);
            Funcs.pay_total = String.valueOf(pay_total);

        } else {
            itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.color.white));
        }


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(item, position);
                        // The adapter will handle updating the selectedPosition and notifying changes
                    }
                }
            }
        });
    }
}

// 3. RecyclerView Adapter
public class RVAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private final List<MyDataItem> data;
    private final Context context;
    private final OnItemCLickListener listener;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public interface OnItemCLickListener {
        void onItemClick(MyDataItem item, int position);
    }

    public RVAdapter(Context context, List<MyDataItem> data, OnItemCLickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
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
        holder.bind(item, listener,position == selectedPosition);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

//    From Here

    public void setSelectedPosition(int position) {
        int previousSelectedPosition = selectedPosition;
        selectedPosition = position;

        // Notify the previously selected item to redraw (to remove selection)
        if (previousSelectedPosition != RecyclerView.NO_POSITION) {
            notifyItemChanged(previousSelectedPosition);
        }
        // Notify the new selected item to redraw (to apply selection)
        if (selectedPosition != RecyclerView.NO_POSITION) {
            notifyItemChanged(selectedPosition);
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public MyDataItem getSelectedItem() {
        if (selectedPosition != RecyclerView.NO_POSITION && selectedPosition < data.size()) {
            return data.get(selectedPosition);
        }
        return null;
    }

}