package com.ganbatee.ugfood.ugfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class home extends AppCompatActivity {
Button btnSI,btnSU;
ImageView text1,text2;
Animation frombottom,fromtop,fade;
TextView text3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btnSI = (Button) findViewById(R.id.btnSI);
        btnSU = (Button) findViewById(R.id.btnSU);
        text1 = (ImageView) findViewById(R.id.text1);
        text2 = (ImageView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fade = AnimationUtils.loadAnimation(this,R.anim.transitionn);

        btnSI.setAnimation(frombottom);
        btnSU.setAnimation(frombottom);
        text1.setAnimation(fromtop);
        text2.setAnimation(fromtop);
        text3.setAnimation(fade);

        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/DroidSans.ttf");
        text3.setTypeface(face);

        btnSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masuk = new Intent(home.this, masuk.class);
                startActivity(masuk);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

        btnSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar = new Intent(home.this, daftar.class);
                startActivity(daftar);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        final AlertDialog.Builder builder = new AlertDialog.Builder(home.this);
        builder.setMessage("Anda yakin untuk keluar ? ");
        builder.setCancelable(true);
        builder.setNegativeButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
