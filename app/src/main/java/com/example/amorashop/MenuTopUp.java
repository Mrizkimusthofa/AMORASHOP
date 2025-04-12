package com.example.amorashop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MenuTopUp extends AppCompatActivity {

    // Declare UI components
    private EditText playerIdEditText, emailEditText;
    private RecyclerView recyclerViewNominal;
    private GridLayout paymentGrid;
    private CheckBox promoCheckBox;
    private Button buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_top_up);

        // Initialize UI components
        initViews();

        // Setup UI components
        setupRecyclerView();
        setupPaymentGrid();
        setupBuyButton();

//        Run Functions
        playerIdEditText.setText(getGameId());
    }

    private void initViews() {
        playerIdEditText = findViewById(R.id.playerIdEditText);
        recyclerViewNominal = findViewById(R.id.recyclerViewNominal);
        paymentGrid = findViewById(R.id.paymentGrid);
        promoCheckBox = findViewById(R.id.promoCheckBox);
        emailEditText = findViewById(R.id.emailEditText);
        buyButton = findViewById(R.id.buyButton);
    }

    private void setupRecyclerView() {
        recyclerViewNominal.setLayoutManager(new LinearLayoutManager(this));
        // Set the adapter for the RecyclerView (implement adapter class)
        // recyclerViewNominal.setAdapter(new YourAdapter());
    }

    private void setupPaymentGrid() {
        // Populate the payment GridLayout (implement this function)
        // Example: varioous payment options can be added from XML or programmatically
    }

    private void setupBuyButton() {
        buyButton.setOnClickListener(v -> handleBuy());
    }

    private void handleBuy() {
        String playerId = playerIdEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        boolean promoApplied = promoCheckBox.isChecked();

        // Validate inputs
        if (playerId.isEmpty()) {
            playerIdEditText.setError("Player ID is required");
            return;
        }
        if (email.isEmpty()) {
            emailEditText.setError("Email is required");

        }

        // Implement API call or transaction logic here
        // Optionally handle promo logic based on promoApplied boolean
    }

    public String getGameId() {
        Intent intent = getIntent();
        String gameId = intent.getStringExtra("gameId");
        return gameId;
    }
}