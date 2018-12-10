package com.example.brioz.siwakaadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brioz.siwakaadmin.Common.Common;
import com.example.brioz.siwakaadmin.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {
    EditText textphone, password;
    Button signin1;

    FirebaseDatabase database;
    DatabaseReference table_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        textphone = (MaterialEditText) findViewById(R.id.textphone);
        password = (MaterialEditText) findViewById(R.id.password);
        signin1 =(Button) findViewById(R.id.signin1);

        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("Users");

        signin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinuser(textphone.getText().toString(),password.getText().toString());

            }
        });

    }

    private void signinuser(String phone, String password) {
        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
        mDialog.setMessage("Please Wait......");
        mDialog.show();

        final String localPhone = phone;
        final String localpassword = password;

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(localPhone).exists()) ;
                {
                    mDialog.dismiss();
                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setTextphone(localPhone);
                    if (Boolean.parseBoolean(user.getIsAdmin())) {
                        if (user.getPassword().equals(localpassword)) {
                            Intent signin = new Intent(SignIn.this, Home1.class);
                            Common.currentUser = user;
                            startActivity(signin);
                            finish();
                        } else {
                            Toast.makeText(SignIn.this, " Failed login::wrong password!!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        mDialog.dismiss();
                        Toast.makeText(SignIn.this, " Failed login:: User Doesn't exist", Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}