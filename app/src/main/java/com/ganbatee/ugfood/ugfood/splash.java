package com.ganbatee.ugfood.ugfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.ganbatee.ugfood.ugfood.Common.Common;
import com.ganbatee.ugfood.ugfood.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class splash extends AppCompatActivity {
private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Init Paper
        Paper.init(this);

        logo = (ImageView) findViewById(R.id.logo);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.transition);
        logo.startAnimation(myanim);


        //check remember
        String user = Paper.book().read(Common.USER_KEY);
        String pw = Paper.book().read(Common.PW_KEY);
        if (user!=null && pw !=null)
        {
            if(!user.isEmpty() && !pw.isEmpty()) {
                Thread time = new Thread() {
                    public void run() {
                        try {
                            sleep(4000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            finish();
                        }

                    }

                };
                time.start();
                login(user, pw);
            }

        }
        else
        {
            final Intent i = new Intent(splash.this,home.class);
            Thread time = new Thread() {
                public void run() {
                    try {
                        sleep(4000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        startActivity(i);
                        finish();
                    }

                }

            };
            time.start();
        }



    }

    private void login(final String phone, final String pw) {

        //init firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Users");


        if (Common.isConnectedToInteger(getBaseContext())){


            table_user.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //mengecek user jika tidak terdaftar dalam database
                    if (dataSnapshot.child(phone).exists()) {

                        final ProgressDialog mDialog = new ProgressDialog(splash.this);
                        mDialog.setMessage("Mohon Tunggu ...");
                        mDialog.show();

                        //informasi user
                        mDialog.dismiss();
                        User user = dataSnapshot.child(phone).getValue(User.class);
                        user.setPhone(phone);
                        if (user.getPassword().equals(pw)) {
                            Intent home2 = new Intent(splash.this, home2.class);
                            Common.currentUser = user;
                            startActivity(home2);
                            finish();

                        } else {
                            mDialog.dismiss();
                            Toast.makeText(splash.this, "Password salah !!! ", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        final ProgressDialog mDialog = new ProgressDialog(splash.this);
                        mDialog.setMessage("Mohon Tunggu ...");
                        mDialog.dismiss();
                        Toast.makeText(splash.this, "User belum terdaftar", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else
        {
            Toast.makeText(splash.this, "Mohon Cek Koneksi Internet Kamu", Toast.LENGTH_SHORT).show();
            return;
        }


    }
}
