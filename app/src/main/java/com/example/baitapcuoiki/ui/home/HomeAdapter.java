package com.example.baitapcuoiki.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitapcuoiki.R;
import com.example.baitapcuoiki.model.Drink;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    public interface OnDrinkClickListener {
        void onDrinkClick(Drink drink);
    }

    private final List<Drink> drinkList;
    private final OnDrinkClickListener listener;

    public HomeAdapter(List<Drink> drinkList, OnDrinkClickListener listener) {
        this.drinkList = drinkList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        Drink drink = drinkList.get(position);
        holder.name.setText(drink.getName());
        holder.price.setText(String.valueOf(drink.getPrice()));
        holder.itemView.setOnClickListener(v -> listener.onDrinkClick(drink));
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            price = itemView.findViewById(R.id.text_price);
        }
    }
}
