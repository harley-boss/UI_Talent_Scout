package com.boss.ui_a4_talent_scout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ArtistsActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CODE = 222;
    private FloatingActionButton fab;
    private ArrayList<CharSequence> selectedArtists;
    private ListView artists;
    private String[] artistOptions;
    private boolean canContinue;
    private TextView subtitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_select);
        TextView tvTitle = findViewById(R.id.title);
        subtitle = findViewById(R.id.subTitle);
        tvTitle.setTextColor(getResources().getColor(R.color.color1));
        selectedArtists = new ArrayList<>();
        artists = findViewById(R.id.lstViewGenres);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        setupArtists();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void setupArtists() {
        artistOptions = new String[]{
            "Imagine Dragons",
            "Dr. Dre",
            "Daft Punk",
            "Eminem",
            "Classified",
            "Macho Man Randy Savage",
            "The Roots",
            "Tina Turner",
            "Pink",
            "Whitney Houston",
            "The Wiggles"
        };

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                artistOptions);
        this.artists.setAdapter(adapter);
        this.artists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = artistOptions[position];
                if (!selectedArtists.contains(selectedItem)) {
                    view.setBackgroundColor(getResources().getColor(R.color.color1));
                    selectedArtists.add(artistOptions[position]);
                } else {
                    view.setBackgroundColor(getResources().getColor(R.color.dark_background));
                    selectedArtists.remove(artistOptions[position]);
                }
                if (selectedArtists.size() >= 3) {
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_yes));
                    subtitle.setVisibility(View.INVISIBLE);
                    canContinue = true;
                } else {
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_no));
                    subtitle.setVisibility(View.VISIBLE);
                    canContinue = false;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == fab) {
            if (canContinue) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", REQUEST_CODE);
                returnIntent.putCharSequenceArrayListExtra("artists", selectedArtists);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    }
}
