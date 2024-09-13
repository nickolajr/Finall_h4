package com.example.finall;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finall.models.Product;
import com.example.finall.recyclerview.ProductAdapter;
import com.example.finall.retrofit.ApiClient;
import com.example.finall.retrofit.ProductApi;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private Button viewCartButton;
    private Button createUserButton;
    private Button createProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        viewCartButton = findViewById(R.id.viewCartButton);
        createUserButton = findViewById(R.id.createUserButton);
        createProductButton = findViewById(R.id.createProductButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchProducts();

        viewCartButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });

        createUserButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateUserActivity.class);
            startActivity(intent);
        });

        createProductButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateProductActivity.class);
            startActivity(intent);
        });
    }

    private void fetchProducts() {
        ProductApi productApi = ApiClient.getProductApi();

        productApi.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> productList = response.body();
                    productAdapter = new ProductAdapter(productList, product -> {
                        Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                        intent.putExtra("product", (Serializable) product); // Cast to Serializable
                        startActivity(intent);
                    });
                    recyclerView.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
