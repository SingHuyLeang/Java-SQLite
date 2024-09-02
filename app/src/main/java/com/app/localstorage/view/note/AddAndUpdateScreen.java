package com.app.localstorage.view.note;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.app.localstorage.R;
import com.app.localstorage.controller.NoteController;
import com.app.localstorage.model.NoteModel;
import com.app.localstorage.util.DT;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class AddAndUpdateScreen extends AppCompatActivity {

    // variable
    private EditText txtTitle,txtContent;
    private ExtendedFloatingActionButton fab;

    private NoteController controller;
    private boolean isUpdate = false;
    private int id = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update);

        // init
        Toolbar toolbar = findViewById(R.id.toolbar);
        txtTitle = findViewById(R.id.txt_title);
        txtContent = findViewById(R.id.txt_content);
        fab = findViewById(R.id.fab);

        setTitle(null);
        setSupportActionBar(toolbar);

        controller = new NoteController(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("_id");
            txtTitle.setText(bundle.getString("_title"));
            txtContent.setText(bundle.getString("_content"));
            isUpdate = true;
        }

        // calling function
        toolbar.setNavigationOnClickListener(v-> startActivity(new Intent(this, HomeScreen.class)));
        fabOnClick();
    }

    // function
    private void fabOnClick(){
        fab.setOnClickListener(v->{
            String title = txtTitle.getText().toString().isEmpty() ? "Untitled" : txtTitle.getText().toString();
            String content = txtContent.getText().toString();
            if (!isUpdate) {
                if (controller.insert(new NoteModel(title,content, DT.date(), DT.time()))) {
                    txtTitle.setText("");
                    txtContent.setText("");
                }
            } else {
                if (controller.update(id,new NoteModel(title,content, DT.date(), DT.time()))) {
                    txtTitle.setText("");
                    txtContent.setText("");
                }
            }
        });
    }

}
