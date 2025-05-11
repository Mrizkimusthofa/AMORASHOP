package com.example.amorashop;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

public class Funcs {
    String username;
    String params;
    String accServer;
    String[] gameIdLists = new String[]{
            "mlbb", "ff", "gi", "hok", "pubgm", "valo", "codm", "eafc", "mcgg", "sg", "sos", "lol", "aov", "lolw", "ss", "rm", "zzz", "hsr"
    };
    String[] gameUrlParams = new String[]{
            "mobile-legends", "free-fire", "genshin", "honor-of-kings", "pubgm-global", "valorant", "cod-mobile", "eafc", "mobile-legends", "sg", "sos", "lol", "arena-of-valor", "lolw", "ss", "rm", "zzz", "hsr"
    };
    String[] accServers = new String[]{
        "Asia", "America", "Europe", "TW, HK, MO"
    };

    String baseUrl = "https://id-game-checker.p.rapidapi.com/";

//    Capitalize String
    public String toCapitalized(String str) {
        String[] words = str.split(" ");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) { // Handle empty words (e.g., multiple spaces)
                capitalized.append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return capitalized.toString().trim();
    }

//    Format string to idr
    public static String formatIdr(String amountString) {
        try {
            // Remove any non-digit characters except the decimal point
            String cleanAmountString = amountString.replaceAll("[^\\d.]", "");

            // Parse the cleaned string as a double
            double amount = Double.parseDouble(cleanAmountString);

            // Create a NumberFormat instance for Indonesian Rupiah (IDR)
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

            // Cast to DecimalFormat to customize the pattern
            DecimalFormat decimalFormat = (DecimalFormat) currencyFormat;

            // Set the pattern to remove trailing zeros after the decimal point
            decimalFormat.applyPattern("Rp#,##0.##"); // Allows 0, 1, or 2 decimal places

            // Format the amount as currency
            return currencyFormat.format(amount);

        } catch (NumberFormatException e) {
            // Handle invalid input (e.g., non-numeric string)
            return "Invalid Amount";
        }
    }

    public interface UsernameCallback {
        void onUsernameReceived(String username);
        void onError(String errorMessage);
    }

    public void getUsernameA (Context context, String accId, String zonaId, UsernameCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl + params + "/" + accId + "/" + zonaId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Integer status = jsonObject.getInt("status");

                            System.out.println(status);

                            if (status == 404) {
                                username = "Username not found!";
                                return;
                            }

                            JSONObject data = jsonObject.getJSONObject("data");
                            String dataUsername = data.getString("username");

//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                            username = dataUsername;
                            callback.onUsernameReceived(username);


                        } catch (JSONException e) {
                            callback.onError("User not found!");
                            throw new RuntimeException(e);

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "User not found!",
                                Toast.LENGTH_SHORT).show();
                        callback.onError("User not found!");
                    }
                }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<>();
                    params.put("x-rapidapi-key", "51865eec7emsh364c08944f4c833p1449a4jsnb662cc75e056");
                    params.put("x-rapidapi-host", "id-game-checker.p.rapidapi.com");
                    return params;
                }
        };
        queue.add(stringRequest);
    }

    public void getUsernameB (Context context, String accId, UsernameCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl + params + "/" + accId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Integer statusCode = jsonObject.getInt("status");

                            System.out.println(statusCode);

                            if (statusCode == 404) {
                                username = "Username not found!";
                                return;
                            }

                            JSONObject data = jsonObject.getJSONObject("data");
                            String dataUsername = data.getString("username");

//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                            username = dataUsername;
                            callback.onUsernameReceived(username);


                        } catch (JSONException e) {
                            callback.onError("User not found!");
                            throw new RuntimeException(e);

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "User not found!",
                                Toast.LENGTH_SHORT).show();
                        callback.onError("User not found!");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("x-rapidapi-key", "51865eec7emsh364c08944f4c833p1449a4jsnb662cc75e056");
                params.put("x-rapidapi-host", "id-game-checker.p.rapidapi.com");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void getUsernameC (Context context, String accId, UsernameCallback callback) {
        System.out.println("in funcs says: "+accServer);
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl + params + "/" + accId + "/" + accServer,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Integer status = jsonObject.getInt("status");

                            System.out.println(status);

                            if (status == 404 || status == 400) {
                                username = "User not found!";
                                callback.onUsernameReceived(username);
                                return;
                            }
                            JSONObject data = jsonObject.getJSONObject("data");
                            String dataUsername = data.getString("username");

//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                            username = dataUsername;
                            callback.onUsernameReceived(username);


                        } catch (JSONException e) {
                            callback.onError("User not found!");
                            throw new RuntimeException(e);

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "User not found!",
                                Toast.LENGTH_SHORT).show();
                        callback.onError("User not found!");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("x-rapidapi-key", "51865eec7emsh364c08944f4c833p1449a4jsnb662cc75e056");
                params.put("x-rapidapi-host", "id-game-checker.p.rapidapi.com");
                return params;
            }
        };
        queue.add(stringRequest);
    }


}
