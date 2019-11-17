package com.boss.ui_a4_talent_scout.Tabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.boss.ui_a4_talent_scout.ArtistsActivity;
import com.boss.ui_a4_talent_scout.ArtistsSelected;
import com.boss.ui_a4_talent_scout.MusicAdapter;
import com.boss.ui_a4_talent_scout.Musician;
import com.boss.ui_a4_talent_scout.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MyArtistsTab extends TabFragment implements View.OnClickListener {
    private View view;
    private View noArtistsView;
    private View artistsView;
    private Button button;
    private GridView gridView;
    private ArrayList<CharSequence> artists = new ArrayList<>();
    private boolean artistsSelected = false;
    private ArrayList<Musician> allMusicians;
    private ArtistsSelected onArtistsSelected;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_my_artists, container, false);
        noArtistsView = view.findViewById(R.id.noArtistsView);
        noArtistsView.setVisibility(View.VISIBLE);
        artistsView = view.findViewById(R.id.artistsView);
        artistsView.setVisibility(View.GONE);
        button = view.findViewById(R.id.btnSetupArtists);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onArtistsSelected = (ArtistsSelected) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ArtistsSelected");
        }
    }

    private void setupGridView() {
        int gridColumnCount = calculateGridColumnCount();
        MusicAdapter adapter = new MusicAdapter(getActivity(), calculateGridItemWidth(gridColumnCount), artists, artists.size());
        allMusicians = adapter.getMusicians();
        onArtistsSelected.onArtistsSelected(allMusicians);
        gridView = view.findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(gridColumnCount);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(position, view);
            }
        });
    }

    private void showPopupMenu(int position, View v) {
        PopupWindow popup;
        final Musician musician = (Musician) gridView.getItemAtPosition(position);
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.popup_layout, null);
        TextView tv = view.findViewById(R.id.tvArtistName);
        CheckBox fb = view.findViewById(R.id.chkFacebook);
        CheckBox tw = view.findViewById(R.id.chkTwitter);
        CheckBox news = view.findViewById(R.id.chkNews);
        CheckBox con = view.findViewById(R.id.chkConcerts);
        fb.setChecked(musician.isFollowingFacebook());
        tw.setChecked(musician.isFollowingTwitter());
        news.setChecked(musician.isFollowingNews());
        con.setChecked(musician.isFollowingConcerts());
        fb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                musician.setFollowingFacebook(isChecked);
            }
        });
        tw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                musician.setFollowingTwitter(isChecked);
            }
        });
        news.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                musician.setFollowingNews(isChecked);
            }
        });
        con.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                musician.setFollowingConcerts(isChecked);
            }
        });
        tv.setText(musician.getName());
        popup = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popup.showAtLocation(view, Gravity.CENTER, 10, 10);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (artistsSelected) {
            setupGridView();
        } else {
            noArtistsView.setVisibility(View.VISIBLE);
            artistsView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            Intent i = new Intent(getActivity(), ArtistsActivity.class);
            startActivityForResult(i, ArtistsActivity.REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ArtistsActivity.REQUEST_CODE) {
                artists = data.getCharSequenceArrayListExtra("artists");
                noArtistsView.setVisibility(View.GONE);
                artistsView.setVisibility(View.VISIBLE);
                artistsSelected = true;
                setupGridView();
            }
        }
    }

    private int calculateGridColumnCount() {
        int screenW = getResources().getDisplayMetrics().widthPixels;
        int defaultItemW = getResources().getDimensionPixelOffset(R.dimen.grid_width);
        return screenW / defaultItemW;
    }

    private int calculateGridItemWidth(int gridColumnCount) {
        int screenW = getResources().getDisplayMetrics().widthPixels;
        return screenW / gridColumnCount;
    }

    public ArrayList<Musician> getAllMusicians() {
        return allMusicians;
    }

    public void onFragmentInteraction(ArrayList<Musician> musicians) {
        allMusicians = musicians;
        //getMusicians();
    }

    private void saveMusicisians(ArrayList<Musician> musicians) {
        SharedPreferences prefs = getActivity().getSharedPreferences(
                "com.boss.ui_a4_talent_scout", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(musicians);
        prefsEditor.putString("musicians", json);
        prefsEditor.apply();
    }

    private ArrayList<Musician> getMusicians() {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("musicians", "");
        Musician musician = gson.fromJson(json, Musician.class);
        return new ArrayList<Musician>();
    }
}
