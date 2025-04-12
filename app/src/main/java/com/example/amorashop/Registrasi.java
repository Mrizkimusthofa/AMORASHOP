package com.example.amorashop;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registrasi extends AppCompatActivity {

    ImageButton backButton;
    EditText fullNameInput, emailInput, passwordInput, confirmPasswordInput;
    Button registerButton;
    TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        // Initialize views
        backButton = findViewById(R.id.backButton1);
        fullNameInput = findViewById(R.id.fullNameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        registerButton = findViewById(R.id.registerButton);
        loginLink = findViewById(R.id.loginLink);

        // Set back button listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registrasi.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: jika ingin menutup SecondActivity setelah kembali
            }
        });

        // Set register button listener
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Add your registration logic here (e.g., validation, API call, etc.)
                if (passwordInput.getText().toString().trim().length() < 8) {
                    Toast.makeText(Registrasi.this, "Password harus lebih dari 8 karakter!", Toast.LENGTH_SHORT).show();
                } else {
                    if (passwordInput.getText().toString().equals(confirmPasswordInput.getText().toString())) {
                        Auth auth = new Auth();
                        auth.register(Registrasi.this, fullNameInput.getText().toString().trim(), emailInput.getText().toString().trim(), passwordInput.getText().toString().trim());
                        Intent intent = new Intent(Registrasi.this, MenuUtama.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Registrasi.this, "Password dan Confirm Password tidak cocok!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set login link listener
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to login activity
                Intent intent = new Intent(Registrasi.this, Loginulang.class);
                startActivity(intent);
            }
        });
    }
}