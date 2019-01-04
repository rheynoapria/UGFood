package com.ganbatee.ugfood.ugfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class daftar extends AppCompatActivity {

    EditText edtPhone,edtName,edtPassword;
    Button btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone_daftar);
        edtName = (MaterialEditText) findViewById(R.id.edtName_daftar);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword_daftar);
        btnDaftar = (Button) findViewById(R.id.btnDaftar);

        //init firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Users");

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Common.isConnectedToInteger(getBaseContext())) {

                    final ProgressDialog mDialog = new ProgressDialog(daftar.this);
                    mDialog.setMessage("Mohon Tunggu ...");
                    mDialog.show();

                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //cek nomor yang sudah terpakai
                            if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                mDialog.dismiss();
                                Toast.makeText(daftar.this, "Nomor Telepon Sudah Terpakai.", Toast.LENGTH_SHORT).show();
                            } else {
                                mDialog.dismiss();
                                User user = new User(edtName.getText().toString(), edtPassword.getText().toString());
                                table_user.child(edtPhone.getText().toString()).setValue(user);
                                Toast.makeText(daftar.this, "Pendaftaran Berhasil.", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(daftar.this, home.class);
                                startActivity(home);
                                finish();
                                return;
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(daftar.this, "Mohon Cek Koneksi Internet Kamu", Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });

    }
}
