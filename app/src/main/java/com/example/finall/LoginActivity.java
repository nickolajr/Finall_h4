package com.example.finall;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.finall.models.User;
import com.example.finall.retrofit.ApiClient;
import com.example.finall.retrofit.AuthApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText mailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mailEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String mail = mailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Make sure they types anything
            if (mail.isEmpty()) {
                mailEditText.setError("mail required");
                return;
            }
            if (password.isEmpty()) {
                passwordEditText.setError("Password required");
                return;
            }


            User user = new User();
            user.setMail(mail);
            user.setPassword(password);

            // Authenticate
            AuthApi authApi = ApiClient.getAuthApi();
            authApi.authenticateUser(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (!response.isSuccessful()) {
                        // On success
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // login failure
                        mailEditText.setError("Invalid username or password");
                        passwordEditText.setError("Invalid username or password");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {


                    // request failure
                    mailEditText.setError("Login failed. Please try again.");
                    passwordEditText.setError("Login failed. Please try again.");
                }
            });
        });
    }
}
