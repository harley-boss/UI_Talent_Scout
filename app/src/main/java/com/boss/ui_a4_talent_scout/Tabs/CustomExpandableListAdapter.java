package com.boss.ui_a4_talent_scout.Tabs;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.boss.ui_a4_talent_scout.R;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}













































/*package com.boss.ui_a4_talent_scout.Tabs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.boss.ui_a4_talent_scout.ExpandableViewHolder;
import com.boss.ui_a4_talent_scout.Musician;
import com.boss.ui_a4_talent_scout.R;

import java.util.ArrayList;

public class ExpandableMusicAdapter extends BaseExpandableListAdapter {
    private final Activity activity;
    private final LayoutInflater layoutInflater;
    private final ArrayList<Musician> musicians;

    public ExpandableMusicAdapter(Activity activity, ArrayList<Musician> musicians) {
        this.activity = activity;
        this.musicians = musicians;
        layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public int getChildCount(Musician musician) {
        int totalCount = 0;
        if (musician.isFollowingConcerts()) {
            totalCount++;
        }
        if (musician.isFollowingFacebook()) {
            totalCount++;
        }
        if (musician.isFollowingNews()) {
            totalCount++;
        }
        if (musician.isFollowingTwitter()) {
            totalCount++;
        }
        return totalCount;
    }

    private Object findChild(Musician musician, int childPosition) {
        Object o = new Object();
        switch (childPosition) {
            case 0:
                o = musician.getNews();
                break;
            case 1:
                o = musician.getFacebook();
                break;
            case 2:
                o = musician.getTwitter();
                break;
            case 3:
                o = musician.getConcerts();
                break;
        }
        return o;
    }

    @Override
    public int getGroupCount() {
        return musicians.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return getChildCount(musicians.get(groupPosition));
    }

    @Override
    public Object getGroup(int groupPosition) {
        return musicians.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return findChild(musicians.get(groupPosition), childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ExpandableViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.musician_list_item_header, parent, false);
            viewHolder = new ExpandableViewHolder(activity);
            viewHolder.title = (TextView)convertView.findViewById(R.id.tvArtistTitle);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ExpandableViewHolder)convertView.getTag();
        }
        viewHolder.update("Fill in @expandablemusicadapter.java - 112");
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ExpandableViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.musician_list_item_header, parent, false);
            viewHolder = new ExpandableViewHolder(activity);
            viewHolder.title = (TextView)convertView.findViewById(R.id.tvArtistTitle);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ExpandableViewHolder)convertView.getTag();
        }
        viewHolder.update("Fill in @expandablemusicadapter.java - 129");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}*/
