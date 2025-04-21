package com.example.amorashop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MenuUtama extends AppCompatActivity {
    ImageView mlbb, ff, gi, hok, pubgm, valo, codm, eafc, mcgg, sg, sos, lol, aov, lolw, ss, rm, zzz, hsr;
    List btnGames = new ArrayList<>();
    MTUDynamicLayout mtudl = new MTUDynamicLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);


//          Init Views
        mlbb = findViewById(R.id.mlbb);
        ff = findViewById(R.id.ff);
        gi = findViewById(R.id.gi);
        hok = findViewById(R.id.hok);
        pubgm = findViewById(R.id.pubgm);
        valo = findViewById(R.id.valo);
        codm = findViewById(R.id.codm);
        eafc = findViewById(R.id.eafc);
        mcgg = findViewById(R.id.mcgg);
        sg = findViewById(R.id.sg);
        sos = findViewById(R.id.sos);
        lol = findViewById(R.id.lol);
        aov = findViewById(R.id.aov);
        lolw = findViewById(R.id.lolw);
        ss = findViewById(R.id.ss);
        rm = findViewById(R.id.rm);
        zzz = findViewById(R.id.zzz);
        hsr = findViewById(R.id.hsr);

//        Assign views to List
        btnGames.add(mlbb);
        btnGames.add(ff);
        btnGames.add(gi);
        btnGames.add(hok);
        btnGames.add(pubgm);
        btnGames.add(valo);
        btnGames.add(codm);
        btnGames.add(eafc);
        btnGames.add(mcgg);
        btnGames.add(sg);
        btnGames.add(sos);
        btnGames.add(lol);
        btnGames.add(aov);
        btnGames.add(lolw);
        btnGames.add(ss);
        btnGames.add(rm);
        btnGames.add(zzz);
        btnGames.add(hsr);


//        Run Functions
        sendGameId(btnGames);


    }

//    Functions
    public void sendGameId(List<View> view) {
        for (View e : view) {
            e.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String gameId = v.getResources().getResourceEntryName(v.getId());

                    mtudl.getLayout(MenuUtama.this, gameId);

//                    Intent intent = new Intent(MenuUtama.this, MTUDynamicLayout.class);
//                    intent.putExtra("gameId", gameId);
//                    startActivity(intent);
                }
            });
        }
    }
}