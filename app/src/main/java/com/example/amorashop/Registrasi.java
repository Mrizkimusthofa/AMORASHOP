package com.example.amorashop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
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
    boolean isPasswordVisible = false;


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

        setupPasswordVisibilityToggle(passwordInput);
        setupPasswordVisibilityToggle(confirmPasswordInput);
    }
    @SuppressLint("ClickableViewAccessibility")
    private void setupPasswordVisibilityToggle(EditText editText) {
        editText.setOnTouchListener((v, event) -> {
            final int DRAWABLE_END = 2; // Index untuk drawableEnd (mata)
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_END].getBounds().width())) {
                    // Toggle visible/invisible password
                    if (isPasswordVisible) {
                        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key_24, 0, R.drawable.ic_eye_closed, 0);
                    } else {
                        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key_24, 0, R.drawable.ic_eye_open, 0);
                    }
                    isPasswordVisible = !isPasswordVisible;

                    // Tetap menjaga posisi cursor di akhir teks
                    editText.setSelection(editText.getText().length());
                    v.performClick();

                    return true;
                }
            }
            return false;
        });

    // Set back button listener
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Registrasi.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Set register button listener
        registerButton.setOnClickListener(v -> {

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
        });

        // Set login link listener
        loginLink.setOnClickListener(v -> {
                Intent intent = new Intent(Registrasi.this, Loginulang.class);
                startActivity(intent);
        });
    }
}