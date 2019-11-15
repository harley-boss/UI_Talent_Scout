package com.boss.ui_a4_talent_scout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GenreActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CODE = 222;
    private FloatingActionButton fab;
    private ArrayList<CharSequence> selectedGenres;
    private ListView genres;
    private String[] genreOptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_select);
        selectedGenres = new ArrayList<>();
        genres = findViewById(R.id.lstViewGenres);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        setupGenres();
    }

    private void setupGenres() {
        genreOptions = new String[]{
            "Rock",
            "Rap",
            "Alternative",
            "Electronic",
            "Hip-Hop",
            "R&B",
            "Country",
            "Smooth Jazz",
            "Funk",
            "Bluegrass",
            "New Age"
        };

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                genreOptions);
        this.genres.setAdapter(adapter);
        this.genres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = genreOptions[position];
                if (!selectedGenres.contains(selectedItem)) {
                    view.setBackgroundColor(getResources().getColor(R.color.color1));
                    selectedGenres.add(genreOptions[position]);
                } else {
                    view.setBackgroundColor(getResources().getColor(R.color.dark_background));
                    selectedGenres.remove(genreOptions[position]);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == fab) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", REQUEST_CODE);
            returnIntent.putCharSequenceArrayListExtra("genres", selectedGenres);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }
}
