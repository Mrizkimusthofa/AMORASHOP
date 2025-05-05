package com.example.amorashop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageButton;
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

public class Loginulang extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button loginButton;
    ImageButton backButton;
    TextView forgetPasswordTextView, registerTextView;
    Auth auth = new Auth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginulang);

//        Session Check
        String sessionKey = auth.getSession(Loginulang.this);
        if(sessionKey != null) {
            Intent intent = new Intent(Loginulang.this, MenuUtama.class);
            startActivity(intent);
            finish(); // Optional: jika ingin menutup SecondActivity setelah kembali
        }

        // Initialize views
        backButton = findViewById(R.id.backButton2);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        forgetPasswordTextView = findViewById(R.id.forgetPasswordTextView);
        registerTextView = findViewById(R.id.registerTextView);

        // Set back button listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finish current activity and return to the previous
            }
        });

        // Set onClickListener for Login Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
                    Toast.makeText(Loginulang.this, "Input field cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                };
                auth.login(Loginulang.this, emailEditText.getText().toString(), passwordEditText.getText().toString());

//                Toast.makeText(Loginulang.this, "Session Key: " + sessionKey, Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(Loginulang.this, MenuUtama.class);
//                startActivity(intent);
            }
        });

        // Set onClickListener for Registration Text
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Registration activity here
                Intent intent = new Intent(Loginulang.this, Registrasi.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for Forget Password Text
        forgetPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle forget password logic here
            }
        });
    }
}