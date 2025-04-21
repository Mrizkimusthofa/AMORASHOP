package com.example.amorashop;
import android.content.Context;
import android.content.SharedPreferences;
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

public class Auth {
    String baseUrl =  "http://192.168.1.7/amora/public/";
    String loginUrl = baseUrl + "login";
    String registerUrl = baseUrl + "register";
    String layoutUrl = baseUrl + "layout/";

    private static final String SHARED_PREF_NAME = "com.amora.sharedpref_key";
    private static final String KEY_SESSION = "session_key";

    public void register(Context context ,String name, String email, String password) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, registerUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String jsonMessage = jsonResponse.getString("message");

                            Toast.makeText(context, jsonMessage,
                                    Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            Toast.makeText(context, "Register failed, email sudah digunakan atau email tidak valid!",
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
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void login(Context context, String email, String password) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String jsonMessage = jsonResponse.getString("message");
                            String sessionKey = jsonResponse.getString("session_key");

                            Toast.makeText(context, jsonMessage,
                                    Toast.LENGTH_SHORT).show();

                            saveSession(context, sessionKey);

                        } catch (JSONException e) {
                            Toast.makeText(context, "Login failed, email atau password salah!",
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
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        queue.add(stringRequest);
    }


//    Session Handle
    public void saveSession(Context context, String sessionValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SESSION, sessionValue);
        editor.apply();
    }

    public String getSession(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SESSION, null); // Returns null if not found
    }

    public void removeSession(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_SESSION);
        editor.apply();
    }
}