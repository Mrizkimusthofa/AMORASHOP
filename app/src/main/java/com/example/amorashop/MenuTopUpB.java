package com.example.amorashop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuTopUpB extends AppCompatActivity {

    // Declare UI components
    private EditText playerIdEditText, emailEditText;
    private RecyclerView recyclerViewNominal;
    private GridLayout paymentGrid;
    private CheckBox promoCheckBox;
    private Button buyButton, btnCheckUsername;
    private TextView tvUsername;
    private List<MyDataItem> data;
    private RVAdapter adapter;

    Funcs funcs = new Funcs();
    List<String> numOfItemsList, itemImagesList, itemPricesList, itemNamesList;
    String gameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_top_up_b);

//        Get Intent Data
        getIntentData();
        for (int i = 0; i < funcs.gameIdLists.length; i++) {
            if(funcs.gameIdLists[i].equals(gameId)) {
                funcs.params = funcs.gameUrlParams[i];
            }
        }

        // Initialize UI components
        initViews();

        // Setup UI components
        setupRecyclerView();
        setupPaymentGrid();
        setupBuyButton();

//        Run Functions
        getUsername();
    }

    private void initViews() {
        playerIdEditText = findViewById(R.id.playerIdEditText);
        recyclerViewNominal = findViewById(R.id.recyclerViewNominal);
        paymentGrid = findViewById(R.id.paymentGrid);
        promoCheckBox = findViewById(R.id.promoCheckBox);
        emailEditText = findViewById(R.id.emailEditText);
        buyButton = findViewById(R.id.buyButton);
        btnCheckUsername = findViewById(R.id.btnCheckUsername);
        tvUsername = findViewById(R.id.tvUsername);
    }

    private void getUsername() {
        btnCheckUsername.setOnClickListener(v -> {
            String playerId = playerIdEditText.getText().toString().trim();
            if (playerId.isEmpty()) {
                playerIdEditText.setError("Player ID is required");
                return;
            }
            funcs.getUsernameB(this, playerId, new Funcs.UsernameCallback() {
                @Override
                public void onUsernameReceived(String username) {
                    // This code will run when the username is ready
                    runOnUiThread(() -> {
                        tvUsername.setText(username);
                    });
                }

                @Override
                public void onError(String e) {
                    // Handle error here
                    runOnUiThread(() -> {
                        tvUsername.setText("User not found!");
                    });
                }
            });
        });
    }

    private void setupRecyclerView() {
//        recyclerViewNominal.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewNominal.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        // Set the adapter for the RecyclerView (implement adapter class)
        // recyclerViewNominal.setAdapter(new YourAdapter());

        data = new ArrayList<>();

        for (int i = 0; i < numOfItemsList.size(); i++) {
            if (numOfItemsList.get(i).equals("1")) {
                data.add(new MyDataItem(itemNamesList.get(i), funcs.formatIdr(itemPricesList.get(i)), itemImagesList.get(i)));
                continue;
            }
            data.add(new MyDataItem(numOfItemsList.get(i)+" "+itemNamesList.get(i), funcs.formatIdr(itemPricesList.get(i)), itemImagesList.get(i)));
        }

//        data.add(new MyDataItem("5 Diamond","Rp. 10.000","https://cdn1.codashop.com/S/content/common/images/denom-image/MLBB/150x150/10_MLBB_NewDemom.png"));
//        data.add(new MyDataItem("59 Diamond", "Rp. 21.000", "https://cdn1.codashop.com/S/content/common/images/denom-image/MLBB/150x150/50_MLBB_NewDemom.png"));
//        data.add(new MyDataItem("17 Diamond", "Rp. 43.000","https://cdn1.codashop.com/S/content/common/images/denom-image/MLBB/150x150/150x250_MLBB_NewDemom.png"));

        adapter = new RVAdapter(this, data);
        recyclerViewNominal.setAdapter(adapter);
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

    public void getIntentData() {
        Intent intent = getIntent();
        String[] numOfItemsArray = intent.getStringArrayExtra("numOfItems");
        String[] itemImagesArray = intent.getStringArrayExtra("itemImages");
        String[] itemPricesArray = intent.getStringArrayExtra("itemPrices");
        String[] itemNamesArray = intent.getStringArrayExtra("itemNames");
        gameId = intent.getStringExtra("gameId");

        numOfItemsList = Arrays.asList(numOfItemsArray);
        itemImagesList = Arrays.asList(itemImagesArray);
        itemPricesList = Arrays.asList(itemPricesArray);
        itemNamesList = Arrays.asList(itemNamesArray);
    }
}