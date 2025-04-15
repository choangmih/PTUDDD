package com.example.baitapcuoiki.ui.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.baitapcuoiki.R;
import com.example.baitapcuoiki.model.Drink;
import java.util.List;

public class Cartadapter extends RecyclerView.Adapter<Cartadapter.ViewHolder> {

    private final List<Drink> cartItems;

    public Cartadapter(List<Drink> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public Cartadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cartadapter.ViewHolder holder, int position) {
        Drink drink = cartItems.get(position);
        holder.name.setText(drink.getName());
        holder.price.setText(String.valueOf(drink.getPrice()));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_drink_name);
            price = itemView.findViewById(R.id.text_drink_price);
        }
    }
}
