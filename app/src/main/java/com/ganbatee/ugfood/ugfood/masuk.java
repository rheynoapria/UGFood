package com.ganbatee.ugfood.ugfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ganbatee.ugfood.ugfood.Common.Common;
import com.ganbatee.ugfood.ugfood.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import io.paperdb.Paper;

public class masuk extends AppCompatActivity {
    EditText edtPhone,edtPassword;
    Button btnMasuk;
    String kosong = "";
    com.rey.material.widget.CheckBox ckbRemember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        btnMasuk = (Button) findViewById(R.id.btnMasuk);
        ckbRemember = (com.rey.material.widget.CheckBox) findViewById(R.id.ckbRemember);

        //init paper
        Paper.init(this);


        //init firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Users");

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Common.isConnectedToInteger(getBaseContext())){

                    //save user  & password
                    if (ckbRemember.isChecked())
                    {
                        Paper.book().write(Common.USER_KEY,edtPhone.getText().toString());
                        Paper.book().write(Common.PW_KEY,edtPassword.getText().toString());

                    }


                    final ProgressDialog mDialog = new ProgressDialog(masuk.this);
                    mDialog.setMessage("Mohon Tunggu ...");
                    mDialog.show();

                    if(edtPhone.getText().toString().matches("")){
                        mDialog.dismiss();
                        Toast.makeText(masuk.this, " Anda Belum Memasukan Data", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    table_user.addValueEventListener(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            //mengecek user jika tidak terdaftar dalam database
                            if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {


                                //informasi user
                                mDialog.dismiss();
                                User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                                user.setPhone(edtPhone.getText().toString());
                                if (user.getPassword().equals(edtPassword.getText().toString())) {
                                    Intent home2 = new Intent(masuk.this, home2.class);
                                    Common.currentUser = user;
                                    startActivity(home2);
                                    finish();

                                } else {
                                    mDialog.dismiss();
                                    Toast.makeText(masuk.this, "Password salah !!! ", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                mDialog.dismiss();
                                Toast.makeText(masuk.this, "User belum terdaftar", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(masuk.this, "Mohon Cek Koneksi Internet Kamu", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        });
    }
}
