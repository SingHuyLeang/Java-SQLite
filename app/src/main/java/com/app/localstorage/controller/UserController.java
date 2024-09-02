package com.app.localstorage.controller;

import android.content.Context;
import android.widget.Toast;

import com.app.localstorage.database.UserDatabase;
import com.app.localstorage.model.UserModel;

public class UserController extends UserDatabase {

    public UserController(Context context) {
        super(context);
    }

    @Override
    public boolean signUp(UserModel user) {
        return super.signUp(user);
    }

    @Override
    public boolean signIn(UserModel user) {
       return super.signIn(user);
    }
}
