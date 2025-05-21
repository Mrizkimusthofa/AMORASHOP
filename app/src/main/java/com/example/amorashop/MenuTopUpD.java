package com.example.amorashop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuTopUpD extends AppCompatActivity {

    // Declare UI components
    private EditText playerIdEditText, emailEditText;
    private RecyclerView recyclerViewNominal;
    private ImageView btnGopay, btnQris, btnDana, btnOvo, btnShopeePay;
    private ConstraintLayout clGopay, clQris, clDana, clOvo, clShopeePay;
    private CheckBox promoCheckBox;
    private Button buyButton;
    private List<MyDataItem> data;
    private RVAdapter adapter;

    Funcs funcs = new Funcs();
    List<String> numOfItemsList, itemImagesList, itemPricesList, itemNamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_top_up_d);

//        Get Intent Data
        getIntentData();

        // Initialize UI components
        initViews();

        // Setup UI components
        setupRecyclerView();

        ImageView[] pmName = {
                btnQris, btnGopay, btnDana, btnOvo, btnShopeePay
        };
        ConstraintLayout[] pmLayout = {
                clQris, clGopay, clDana, clOvo, clShopeePay
        };
        Funcs.setupPaymentGrid(this, pmName, pmLayout);

        Funcs.id_account = String.valueOf(0);
        Funcs.nickname = "unknown";

        setupBuyButton();

//        Run Functions
    }

    private void initViews() {
        playerIdEditText = findViewById(R.id.playerIdEditText);
        recyclerViewNominal = findViewById(R.id.recyclerViewNominal);
        promoCheckBox = findViewById(R.id.promoCheckBox);
        emailEditText = findViewById(R.id.emailEditText);
        buyButton = findViewById(R.id.buyButton);
        btnGopay = findViewById(R.id.pmGopay);
        btnQris = findViewById(R.id.pmQris);
        btnDana = findViewById(R.id.pmDana);
        btnOvo = findViewById(R.id.pmOvo);
        btnShopeePay = findViewById(R.id.pmShopeePay);
        clGopay = findViewById(R.id.clGopay);
        clQris = findViewById(R.id.clQris);
        clDana = findViewById(R.id.clDana);
        clOvo = findViewById(R.id.clOvo);
        clShopeePay = findViewById(R.id.clShopeePay);
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

        adapter = new RVAdapter(this, data, this::onItemClick);
        recyclerViewNominal.setAdapter(adapter);
    }

    public void onItemClick(MyDataItem item, int position) {
        // Logic to change background:
        // 1. Tell the adapter which item is now selected
        adapter.setSelectedPosition(position);

        // 2. Optionally, do something else with the selected item
        MyDataItem selectedItem = adapter.getSelectedItem();
        if (selectedItem != null) {
            // You can use the selectedItem data
        }
    }

    private void setupBuyButton() {
        buyButton.setOnClickListener(v -> handleBuy());
    }

    private void handleBuy() {
//        String playerId = playerIdEditText.getText().toString().trim();
//        String email = emailEditText.getText().toString().trim();
//        boolean promoApplied = promoCheckBox.isChecked();

        // Validate inputs
//        if (playerId.isEmpty()) {
//            playerIdEditText.setError("Player ID is required");
//            return;
//        }
//        if (email.isEmpty()) {
//            emailEditText.setError("Email is required");
//
//        }

        Intent intent = new Intent(MenuTopUpD.this, KonfirmasiPembayaran.class);
        startActivity(intent);

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
}