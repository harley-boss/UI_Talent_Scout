package com.boss.ui_a4_talent_scout;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class
MusicAdapter extends BaseAdapter {
    private ArrayList<CharSequence> artists;
    private ArrayList<Musician> musicians;
    private LayoutInflater layoutInflater;
    private Activity activity;
    private int itemWidth;
    private int previous = 1;
    private int current = 1;
    private int itemCount = 0;

    public MusicAdapter(Activity activity, int itemWidth, ArrayList<CharSequence> artists, int itemCount) {
        this.artists = artists;
        this.activity = activity;
        this.itemWidth = itemWidth;
        this.itemCount = itemCount;
        this.layoutInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        setupMusicians(artists);
    }

    private void setupMusicians(ArrayList<CharSequence> artists) {
        musicians = new ArrayList<>();
        for (CharSequence c : artists) {
            musicians.add(new Musician(c.toString(), ""));
        }
    }

    @Override
    public int getCount() {
        return itemCount;
    }

    @Override
    public Object getItem(int position) {
        return musicians.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MusicViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.album_art, parent, false);
            viewHolder = new MusicViewHolder(activity);
            viewHolder.imageView = convertView.findViewById(R.id.imgCover);
            previous = current;
            while (current <= previous) {
                switch (current) {
                    case 2:
                        viewHolder.imageView.setImageResource(R.drawable.cover_2);
                        break;
                    case 3:
                        viewHolder.imageView.setImageResource(R.drawable.cover_3);
                        break;
                    case 4:
                        viewHolder.imageView.setImageResource(R.drawable.cover_4);
                        break;
                    case 5:
                        viewHolder.imageView.setImageResource(R.drawable.cover_5);
                        break;
                    case 6:
                        viewHolder.imageView.setImageResource(R.drawable.cover_6);
                        break;
                    case 7:
                        viewHolder.imageView.setImageResource(R.drawable.cover_7);
                        break;
                    case 8:
                        viewHolder.imageView.setImageResource(R.drawable.cover_8);
                        break;
                    case 9:
                        viewHolder.imageView.setImageResource(R.drawable.cover_9);
                        break;
                    case 10:
                        viewHolder.imageView.setImageResource(R.drawable.cover_10);
                        break;
                    case 11:
                        viewHolder.imageView.setImageResource(R.drawable.cover_11);
                        break;
                    case 12:
                        viewHolder.imageView.setImageResource(R.drawable.cover_12);
                        break;
                    default:
                        viewHolder.imageView.setImageResource(R.drawable.cover_1);
                }
                current++;
            }
            convertView.setTag(viewHolder);
            convertView.getLayoutParams().width = itemWidth;
        } else {
            viewHolder = (MusicViewHolder)convertView.getTag();
        }
        viewHolder.update();
        return convertView;
    }

    public ArrayList<Musician> getMusicians() {
        return musicians;
    }
}
