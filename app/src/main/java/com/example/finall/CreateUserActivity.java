package com.example.finall;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.finall.retrofit.UserApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finall.models.User;
import com.example.finall.retrofit.ApiClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserActivity extends AppCompatActivity {

    private EditText userNameEditText;
    private EditText userMailEditText;
    private EditText userPasswordEditText;
    private Button createUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        userNameEditText = findViewById(R.id.userNameEditText);
        userMailEditText = findViewById(R.id.userMailEditText);
        userPasswordEditText = findViewById(R.id.userPasswordEditText);
        createUserButton = findViewById(R.id.createUserButton);

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void createUser() {
        String name = userNameEditText.getText().toString();
        String mail = userMailEditText.getText().toString();
        String password = userPasswordEditText.getText().toString();

        User user = new User();
        user.setName(name);
        user.setMail(mail);
        user.setPassword(password);

        UserApi userApi = ApiClient.getRetrofitInstance().create(UserApi.class);
        Call<User> call = userApi.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreateUserActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateUserActivity.this, "Failed to create user.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(CreateUserActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
