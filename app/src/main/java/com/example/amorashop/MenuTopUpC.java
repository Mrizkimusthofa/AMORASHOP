package com.example.amorashop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuTopUpC extends AppCompatActivity {

    // Declare UI components
    private EditText playerIdEditText, emailEditText;
    private RecyclerView recyclerViewNominal;
    private Spinner spinServer;
    private CheckBox promoCheckBox;
    private Button buyButton;
    private List<MyDataItem> data;
    private RVAdapter adapter;

    Funcs funcs = new Funcs();
    List<String> numOfItemsList, itemImagesList, itemPricesList, itemNamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_top_up_c);

//        Get Intent Data
        getIntentData();

        // Initialize UI components
        initViews();

        // Setup UI components
        setupRecyclerView();
        setupPaymentGrid();
        setupBuyButton();

//        Run Functions
        spinServerFunc();

    }

    private void initViews() {
        playerIdEditText = findViewById(R.id.playerIdEditText);
        recyclerViewNominal = findViewById(R.id.recyclerViewNominal);
        spinServer = findViewById(R.id.spinServer);
        promoCheckBox = findViewById(R.id.promoCheckBox);
        emailEditText = findViewById(R.id.emailEditText);
        buyButton = findViewById(R.id.buyButton);
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

        numOfItemsList = Arrays.asList(numOfItemsArray);
        itemImagesList = Arrays.asList(itemImagesArray);
        itemPricesList = Arrays.asList(itemPricesArray);
        itemNamesList = Arrays.asList(itemNamesArray);
    }

    public void spinServerFunc() {
        List<String> items = new ArrayList<>();
        items.add("America");
        items.add("Europe");
        items.add("Asia");
        items.add("TW, HK, MO");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinServer.setAdapter(adapter);

//        Handle Item Selection
        spinServer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = items.get(position);
                Toast.makeText(MenuTopUpC.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                // Do something with the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Called when the selection disappears
            }
        });
    }
}