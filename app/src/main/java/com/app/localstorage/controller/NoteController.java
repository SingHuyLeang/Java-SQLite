package com.app.localstorage.controller;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.app.localstorage.database.NoteDatabase;
import com.app.localstorage.model.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class NoteController extends NoteDatabase {
    Context context;
    public NoteController(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    // feature
    @Override
    public boolean insert(NoteModel model) {
        if (model.getContent().isEmpty()) {
            Toast.makeText(context, "Content of note con not empty.", Toast.LENGTH_SHORT).show();
        } else if (super.insert(model)){
            return true;
        } else {
            Toast.makeText(context, "Your note can not add.", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public List<NoteModel> getAll() {
        if (!super.getAll().isEmpty()) {
            return super.getAll();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(int id, NoteModel note) {
        return super.update(id, note);
    }

    @Override
    public boolean delete(int id) {
        if ( id > 0 && super.delete(id)) {
            Toast.makeText(context, "Deleted Success", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "Delete Not Success", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
