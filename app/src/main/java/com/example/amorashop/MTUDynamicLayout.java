package com.example.amorashop;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MTUDynamicLayout {
    Auth auth = new Auth();

    String layoutType;
    String itemName;
    List<String> numOfItemsList = new ArrayList<>();
    List<String> itemImagesList = new ArrayList<>();
    List<String> itemPricesList = new ArrayList<>();
    List<String> itemNamesList = new ArrayList<>();

    String[] numOfItemsArray, itemImagesArray, itemPricesArray, itemNamesArray;


//    List<String> intentDataList = new ArrayList<>();
//    String[] intentDataArray;

    public void getLayout(Context context, String id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, auth.layoutUrl+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
//                            Get RAW JSON Response
                            JSONObject jsonResponse = new JSONObject(response);
//                            Get ARRAY of JSON (Array->Object->String)
                            JSONArray jsonLayout = jsonResponse.getJSONArray("layouts");
//                            Get Data on specific array index (Object->String)
                            JSONObject jsonLayoutObject = jsonLayout.getJSONObject(0);
//                            Get Data on specific object key (String)
                            layoutType = jsonLayoutObject.getString("layout_type");

                            for (int i = 0; i < jsonLayout.length(); i++) {
                                JSONObject layoutObj = jsonLayout.getJSONObject(i);
                                numOfItemsList.add(layoutObj.getString("item_amounts"));
                                itemImagesList.add(layoutObj.getString("item_image"));
                                itemPricesList.add(layoutObj.getString("item_price"));
                                itemNamesList.add(layoutObj.getString("item_name"));

//                                Get All Rows Data
//                                JSONObject jsonLayoutItem = jsonLayout.getJSONObject(i);
//                                Get specific datas and assign to variable
//                                layoutType = jsonLayoutItem.getString("layout_type");
//                                numOfItems = new String[]{jsonLayoutItem.getString("item_amounts")};
//                                itemImages = new String[]{jsonLayoutItem.getString("item_image")};
//                                itemPrices = new String[]{jsonLayoutItem.getString("item_price")};
                            }

//                            Handle It
                            if (layoutType.equals("a")) {
                                numOfItemsArray = numOfItemsList.toArray(new String[0]);
                                itemImagesArray = itemImagesList.toArray(new String[0]);
                                itemPricesArray = itemPricesList.toArray(new String[0]);
                                itemNamesArray = itemNamesList.toArray(new String[0]);
                                Intent intent = new Intent(context, MenuTopUp.class);
                                intent.putExtra("numOfItems", numOfItemsArray);
                                intent.putExtra("itemImages", itemImagesArray);
                                intent.putExtra("itemPrices", itemPricesArray);
                                intent.putExtra("itemNames", itemNamesArray);

                                context.startActivity(intent);
                            } else {
                                Toast.makeText(context, "Layout type not found!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(context, "Kesalahan saat request layout data!",
                                    Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(stringRequest);
    }
}
