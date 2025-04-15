package com.example.baitapcuoiki.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.baitapcuoiki.R;
import com.example.baitapcuoiki.model.Drink;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private Cartadapter adapter;
    private List<Drink> cartList;

    public CartFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.recycler_cart);
        Button btnOrder = view.findViewById(R.id.button_place_order);

        cartList = new ArrayList<>(); // Tạm thời dữ liệu test cứng
        adapter = new Cartadapter(cartList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        btnOrder.setOnClickListener(v -> {
            // Thực hiện logic đặt hàng ở đây (Firebase push)
        });

        return view;
    }

    public void addToCart(Drink drink) {
        cartList.add(drink);
        adapter.notifyDataSetChanged();
    }
}
