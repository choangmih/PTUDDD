package com.example.baitapcuoiki.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitapcuoiki.R;
import com.example.baitapcuoiki.model.Drink;
import com.example.baitapcuoiki.ui.drink.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private List<Drink> drinkList;

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cartergory, container, false);
        recyclerView = view.findViewById(R.id.recycler_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        drinkList = new ArrayList<>();
        adapter = new HomeAdapter(drinkList, drink -> {
            // Mở DrinkFragment để xem chi tiết món
            Fragment drinkFragment = DrinkFragment.newInstance(drink);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, drinkFragment)
                    .addToBackStack(null)
                    .commit();
        });

        recyclerView.setAdapter(adapter);
        loadDrinksFromFirebase();

        return view;
    }

    private void loadDrinksFromFirebase() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Drinks");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                drinkList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Drink drink = data.getValue(Drink.class);
                    if (drink != null) {
                        drinkList.add(drink);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}
