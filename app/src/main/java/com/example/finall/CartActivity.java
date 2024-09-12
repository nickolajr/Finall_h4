package com.example.finall;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finall.models.CartItem;

import java.util.ArrayList;
import java.util.List;
public class CartActivity extends AppCompatActivity {

    private TextView cartTextView;
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartTextView = findViewById(R.id.cartTextView);

        // Retrieve cart items from CartManager
        cartItems = CartManager.getInstance().getCartItems();

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        // Display cart items
        displayCartItems();
    }

    private void displayCartItems() {
        StringBuilder cartDisplay = new StringBuilder();

        for (CartItem cartItem : cartItems) {
            cartDisplay.append(cartItem.toString()).append("\n\n");
        }

        cartTextView.setText(cartDisplay.toString());
    }
}
