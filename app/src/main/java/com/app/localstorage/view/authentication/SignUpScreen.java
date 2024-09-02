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

public class SignUpScreen extends AppCompatActivity {

    // variable
    private TextView btnGotoSignIn;
    private EditText txtEmail,txtPassword,txtConfirmPassword;
    private AppCompatButton btnSignUp;
    private UserController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // initialize variable
        btnGotoSignIn      = findViewById(R.id.btn_goto_sign_in);
        txtEmail           = findViewById(R.id.txt_email);
        txtPassword        = findViewById(R.id.txt_password);
        txtConfirmPassword = findViewById(R.id.txt_confirm_password);

        btnSignUp          = findViewById(R.id.btn_sign_up);

        controller         = new UserController(this);

        // calling
        btnGotoSignInOnClick();
        btnSignUpOnClick();
    }

    private void btnGotoSignInOnClick(){
        btnGotoSignIn.setOnClickListener(v->finish());
    }
    private void btnSignUpOnClick(){
        btnSignUp.setOnClickListener(v->{
            String email    = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();
            String confirmPassword = txtConfirmPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Confirm password is not equals.", Toast.LENGTH_SHORT).show();
            } else if (controller.signUp(new UserModel(email,password))) {
                startActivity(new Intent(this, HomeScreen.class));
            } else {
                Toast.makeText(this, "Fail to Sign up.", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
