package com.example.baitapcuoiki.ui.cart;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitapcuoiki.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Gắn CartFragment vào activity_cart
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.cart_fragment_container, new CartFragment())
                    .commit();
        }
    }
}
