package com.example.baitapcuoiki.Manager;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitapcuoiki.R;
import com.example.baitapcuoiki.model.Drink;
import com.example.baitapcuoiki.ui.cart.Cartadapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Drink> orderList;
    Cartadapter adapter; // Tái sử dụng CartAdapter để hiển thị danh sách đặt hàng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        recyclerView = findViewById(R.id.recycler_manager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderList = new ArrayList<>();

        adapter = new Cartadapter(orderList);
        recyclerView.setAdapter(adapter);

        loadOrders();
    }

    private void loadOrders() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Cart");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Drink drink = data.getValue(Drink.class);
                    orderList.add(drink);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ManagerActivity.this, "Lỗi tải đơn hàng", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
