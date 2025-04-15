package com.example.baitapcuoiki.ui.drink;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.baitapcuoiki.R;
import com.example.baitapcuoiki.model.Drink;
import com.example.baitapcuoiki.ui.cart.CartActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DrinkFragment extends Fragment {

    private static final String ARG_NAME = "name";
    private static final String ARG_PRICE = "price";

    private String name;
    private double price;

    public static DrinkFragment newInstance(Drink drink) {
        DrinkFragment fragment = new DrinkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, drink.getName());
        args.putDouble(ARG_PRICE, drink.getPrice());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
            price = getArguments().getDouble(ARG_PRICE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drink, container, false);

        TextView nameTextView = view.findViewById(R.id.text_detail_name);
        TextView priceTextView = view.findViewById(R.id.text_detail_price);
        Button orderButton = view.findViewById(R.id.button_order_drink);
        Button viewCartButton = view.findViewById(R.id.button_view_cart); // ⚠️ DI CHUYỂN RA NGOÀI

        nameTextView.setText(name);
        priceTextView.setText(String.format("%,.0f VND", price));

        orderButton.setOnClickListener(v -> {
            DatabaseReference cartRef = FirebaseDatabase.getInstance()
                    .getReference("Cart")
                    .push();

            Drink selectedDrink = new Drink(name, price);
            cartRef.setValue(selectedDrink)
                    .addOnSuccessListener(aVoid -> Toast.makeText(getContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(getContext(), "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        viewCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CartActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
