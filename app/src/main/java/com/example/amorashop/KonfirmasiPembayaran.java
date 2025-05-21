package com.example.amorashop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View; // Added import for View
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class KonfirmasiPembayaran extends AppCompatActivity {

    TextView itemInfo, idInfo, nickname, payMethod, payPrice, payTax, payTotal;
    Button btnConfirm;

    Auth auth = new Auth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran);
        System.out.println(Funcs.item_info);
        System.out.println(Funcs.id_account);
        System.out.println(Funcs.nickname);
        System.out.println(Funcs.payment_method);
        System.out.println(Funcs.pay_price);
        System.out.println(Funcs.pay_tax);
        System.out.println(Funcs.pay_total);


//        Init Views
        initViews();

        viewsSetup();

        btnConfirm.setOnClickListener( v -> {
            createOrder();
        });

    }

    private void initViews() {
        itemInfo = findViewById(R.id.itemInfo);
        idInfo = findViewById(R.id.idInfo);
        nickname = findViewById(R.id.nickname);
        payMethod = findViewById(R.id.payMethod);
        payPrice = findViewById(R.id.payPrice);
        payTax = findViewById(R.id.payTax);
        payTotal = findViewById(R.id.payTotal);
        btnConfirm = findViewById(R.id.confirmButton);
    }

    private void viewsSetup() {
        itemInfo.setText(Funcs.item_info);
        idInfo.setText(Funcs.id_account);
        nickname.setText(Funcs.nickname);
        payMethod.setText(Funcs.payment_method);
        payPrice.setText(Funcs.formatIdr(Funcs.pay_price));
        payTax.setText("Pajak (11%): "+Funcs.formatIdr(Funcs.pay_tax));
        payTotal.setText(Funcs.formatIdr(Funcs.pay_total));



//        For testing

//        itemInfo.setText("3 Diamonds");
//        idInfo.setText("0");
//        nickname.setText("idk");
//        payMethod.setText("QRIS");
//        payPrice.setText("10000");
//        payTax.setText("1100");
//        payTotal.setText("11100");

    }

    private void createOrder() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, auth.createOrder,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Integer status = jsonObject.getInt("status");
                            String message = jsonObject.getString("message");

                            System.out.println(status);

                            if (status == 200) {
                                Toast.makeText(KonfirmasiPembayaran.this, message, Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(KonfirmasiPembayaran.this, MenuUtama.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(KonfirmasiPembayaran.this, "Order failed, please contact our team!", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            Toast.makeText(KonfirmasiPembayaran.this, "Order failed, please contact our team!", Toast.LENGTH_SHORT).show();
                            throw new RuntimeException(e);

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KonfirmasiPembayaran.this, "Order failed, please contact our team!", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_account", Funcs.id_account);
                params.put("nickname", Funcs.nickname);
                params.put("item_info", Funcs.item_info);
                params.put("payment_method", Funcs.payment_method);
                params.put("pay_price", Funcs.pay_price);
                params.put("pay_tax", Funcs.pay_tax);
                params.put("pay_total", Funcs.pay_total);

                return params;
            }
        };
        queue.add(stringRequest);
    }
}