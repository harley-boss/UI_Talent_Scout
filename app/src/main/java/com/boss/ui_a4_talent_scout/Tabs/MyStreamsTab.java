package com.boss.ui_a4_talent_scout.Tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.boss.ui_a4_talent_scout.ExpandableListData;
import com.boss.ui_a4_talent_scout.Musician;
import com.boss.ui_a4_talent_scout.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyStreamsTab extends TabFragment {
    private HashMap<String, List<String>> expandableListDetail;
    private List<String> expandableListTitle;
    private ExpandableListView expandableListView;
    private CustomExpandableListAdapter adapter;
    private View noArtistsView;
    private View artistsView;
    private View musicControls;
    private boolean hasBeenSetup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_my_stream, container, false);
        noArtistsView = view.findViewById(R.id.noArtistsView);
        artistsView = view.findViewById(R.id.artistsView);
        artistsView.setVisibility(View.VISIBLE);
        musicControls = view.findViewById(R.id.musicControls);
        musicControls.setVisibility(View.GONE);
        expandableListView = view.findViewById(R.id.expandableView);
        expandableListView.setVisibility(View.VISIBLE);
        setupListView();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            setupListView();
        }
    }

    private void setupListView() {
        if (adapter == null && getActivity() != null) {
            hasBeenSetup = true;
            expandableListDetail = ExpandableListData.getData();
            expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
            adapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
            expandableListView.setAdapter(adapter);
            expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {

                }
            });
            expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                @Override
                public void onGroupCollapse(int groupPosition) {

                }
            });
            expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    if (adapter.getGroup(groupPosition).toString().equals("Songs")) {
                        String data = adapter.getChild(groupPosition, childPosition).toString();
                        String[] elements = data.split("-");
                        String artist = elements[0].trim();
                        String song = elements[1].trim();
                        musicControls.setVisibility(View.VISIBLE);
                        TextView mc = musicControls.findViewById(R.id.tvMusicControlArtistName);
                        mc.setText(artist);
                        TextView ms = musicControls.findViewById(R.id.tvMusicControlSongName);
                        ms.setText(song);
                    } else {
                        musicControls.setVisibility(View.VISIBLE);
                    }
                    return false;
                }
            });
        }
    }

    public void onFragmentInteraction(ArrayList<Musician> musicians) {

    }
}
