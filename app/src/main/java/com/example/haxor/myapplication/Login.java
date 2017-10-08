package com.example.haxor.myapplication;

//Created by Ben Thompson Oct 2017

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.provider.FirebaseInitProvider;
import com.google.firebase.storage.StorageReference;

public class Login extends AppCompatActivity implements View.OnClickListener {


    LinearLayout l1;
    LinearLayout l2;
    Animation uptodown, downtoup;
    private Button but2;
    private EditText editTextEmail;

    private EditText editText2;
    private Button buttonLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private EditText editText4;

    public void init(){

        firebaseAuth = FirebaseAuth.getInstance();

        //check if user is already logged in
        if(firebaseAuth.getCurrentUser() != null) {
            //Profile activity
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        progressDialog = new ProgressDialog(this);
        but2 = (Button) findViewById(R.id.but2);
        editText4 = (EditText) findViewById(R.id.editText4);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editText2 = (EditText) findViewById(R.id.editText2);

        but2.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);




    }



    private void registerUser(){
        String email= editTextEmail.getText().toString().trim();

        String password = editText2.getText().toString().trim();
        String name = editText4.getText().toString().trim();
        //database




        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }



        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }



        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                }else{
                    Toast.makeText(Login.this, "Could not register...", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }



    @Override
    public void onClick(View view) {
        if (view == but2) {
            registerUser();

        }
        if(view == buttonLogin){

            startActivity(new Intent(this, SignInActivity.class));
        }


        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        init();
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);


    }


}
