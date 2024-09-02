package com.app.localstorage.view.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.app.localstorage.R;
import com.app.localstorage.controller.UserController;
import com.app.localstorage.model.UserModel;
import com.app.localstorage.view.note.HomeScreen;

public class SignInScreen extends AppCompatActivity {

    // variable
    private TextView btnGotoSignUp;
    private AppCompatButton btnSignIn;
    private UserController controller;
    private EditText txtEmail,txtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // initialize variable
        btnGotoSignUp = findViewById(R.id.btn_goto_sign_up);
        btnSignIn     = findViewById(R.id.btn_sign_in);
        txtEmail      = findViewById(R.id.txt_email);
        txtPassword   = findViewById(R.id.txt_password);

        controller    = new UserController(this);

        // calling
        btnGotoSignUpOnClick();
        btnSignInOnClick();
    }

    private void btnGotoSignUpOnClick(){
        btnGotoSignUp.setOnClickListener(v->startActivity(new Intent(this, SignUpScreen.class)));
    }

    private void btnSignInOnClick(){
        btnSignIn.setOnClickListener(v->{
            String email    = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
            } else if (controller.signIn(new UserModel(email,password))){
                startActivity(new Intent(this, HomeScreen.class));
            } else {
                Toast.makeText(this, "Sign in failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
