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
    ImageView ml, ff, gi, hok, pubg, valo, cod, eafc, mc, sg, sos, lol, aov, lolw, ss, rm, zzz, hsr;
    List btnGames = new ArrayList<>();
    MTUDynamicLayout mtudl = new MTUDynamicLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);


//          Init Views
        ml = findViewById(R.id.ml);
        ff = findViewById(R.id.ff);
        gi = findViewById(R.id.gensin);
        hok = findViewById(R.id.hok);
        pubg = findViewById(R.id.pubg);
        valo = findViewById(R.id.valorant);
        cod = findViewById(R.id.cod);
        eafc = findViewById(R.id.easports);
        mc = findViewById(R.id.mcgogo);
        sg = findViewById(R.id.stumble);
        sos = findViewById(R.id.stateofsurvival);
        lol = findViewById(R.id.lol);
        aov = findViewById(R.id.aov);
        lolw = findViewById(R.id.lolwildrift);
        ss = findViewById(R.id.supersus);
        rm = findViewById(R.id.racingmaster);
        zzz = findViewById(R.id.zenlesszonezero);
        hsr = findViewById(R.id.hsr);

//        Assign views to List
        btnGames.add(ml);
        btnGames.add(ff);
        btnGames.add(gi);
        btnGames.add(hok);
        btnGames.add(pubg);
        btnGames.add(valo);
        btnGames.add(cod);
        btnGames.add(eafc);
        btnGames.add(mc);
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