package com.example.amorashop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MenuUtama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        ImageView myImageView = findViewById(R.id.ml);

        myImageView.setOnClickListener(v -> {
            Intent intent = new Intent(MenuUtama.this, MenuTopUp.class);
            startActivity(intent);
        });
    }
}