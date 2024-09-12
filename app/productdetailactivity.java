import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finall.R;
import com.example.finall.models.Product;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        TextView nameTextView = findViewById(R.id.detailProductName);
        TextView priceTextView = findViewById(R.id.detailProductPrice);
        TextView descriptionTextView = findViewById(R.id.detailProductDescription);

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");

        if (product != null) {
            nameTextView.setText(product.getName());
            priceTextView.setText("$" + product.getPrice());
            descriptionTextView.setText(product.getDescription());
        }
    }
}
