package com.app.localstorage.view.note.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.app.localstorage.R;
import com.app.localstorage.controller.NoteController;
import com.app.localstorage.model.NoteModel;
import com.app.localstorage.view.note.AddAndUpdateScreen;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<NoteModel> {

    private final Context context;
    private final List<NoteModel> models;
    private final NoteController controller;

    public ListViewAdapter(Context context, List<NoteModel> models) {
        super(context, R.layout.card_note,models);
        this.context = context;
        this.models  = models;
        controller = new NoteController(context);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.card_note,parent,false);

        CardView card;
        TextView date,time,title,content;
        ImageButton btnDelete;

        card = convertView.findViewById(R.id.card_item);
        date = convertView.findViewById(R.id.txt_date);
        time = convertView.findViewById(R.id.txt_time);
        title = convertView.findViewById(R.id.txt_title);
        content = convertView.findViewById(R.id.txt_content);
        btnDelete = convertView.findViewById(R.id.btn_delete);

        date.setText(models.get(position).getDate());
        time.setText(models.get(position).getTime());
        title.setText(models.get(position).getTitle());
        content.setText(models.get(position).getContent());

        // handle action
        card.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddAndUpdateScreen.class);
            intent.putExtra("_id",models.get(position).getId());
            intent.putExtra("_title",models.get(position).getTitle());
            intent.putExtra("_content",models.get(position).getContent());
            intent.putExtra("_date",models.get(position).getDate());
            intent.putExtra("_time",models.get(position).getTime());
            context.startActivity(intent);
        });

        btnDelete.setOnClickListener(v -> {
            if (controller.delete(models.get(position).getId())) {
                models.remove(position);
            }
            notifyDataSetChanged();
        });

        return convertView;
    }
}
