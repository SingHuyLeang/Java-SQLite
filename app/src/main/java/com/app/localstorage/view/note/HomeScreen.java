package com.app.localstorage.view.note;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.localstorage.R;
import com.app.localstorage.controller.NoteController;
import com.app.localstorage.view.note.adapter.ListViewAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class HomeScreen extends AppCompatActivity {
    // variable
    private ExtendedFloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // init
        fab          = findViewById(R.id.fab);
        ListView listView = findViewById(R.id.list_view);

        NoteController controller = new NoteController(this);
        ListViewAdapter adapter = new ListViewAdapter(this, controller.getAll());
        listView.setAdapter(adapter);

        // calling function
        fabOnClick();
    }

    // build function
    private void fabOnClick(){
        fab.setOnClickListener(v -> startActivity(new Intent(this, AddAndUpdateScreen.class)));
    }
}
