package com.boss.ui_a4_talent_scout.Tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.boss.ui_a4_talent_scout.CustomDialog;
import com.boss.ui_a4_talent_scout.Musician;
import com.boss.ui_a4_talent_scout.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyShoutsTab extends TabFragment implements View.OnClickListener {
    private View noArtistsView;
    private View artistsView;
    private Spinner artistsSpinner;
    private Spinner socialMediaSpinner;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_my_shouts, container, false);
        fab = view.findViewById(R.id.fab);
        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_yes));
        fab.setOnClickListener(this);
        noArtistsView = view.findViewById(R.id.noArtistsView);
        artistsView = view.findViewById(R.id.artistsView);
        artistsView.setVisibility(View.VISIBLE);
        artistsSpinner = view.findViewById(R.id.spnArtists);
        socialMediaSpinner = view.findViewById(R.id.spnMedias);
        setupSpinners();
        return view;
    }

    private void setupSpinners() {
        ArrayList<Musician> musicians = Utilities.generateMusicians();
        ArrayList<String> socialMedia = Utilities.generateSocialMediaSites();
        ArrayList<String> artists = new ArrayList<>();
        for (Musician m : musicians) {
            artists.add(m.getName());
        }
        ArrayAdapter<String> artistAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, artists);
        artistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        artistsSpinner.setAdapter(artistAdapter);

        ArrayAdapter<String> sitesAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, socialMedia);
        sitesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        socialMediaSpinner.setAdapter(sitesAdapter);
    }

    @Override
    public void onClick(View v) {
        CustomDialog dialog = new CustomDialog(getContext());
        dialog.show();
    }
}
