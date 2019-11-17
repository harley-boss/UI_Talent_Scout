package com.boss.ui_a4_talent_scout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.boss.ui_a4_talent_scout.Tabs.MainTab;
import com.boss.ui_a4_talent_scout.Tabs.MyArtistsTab;
import com.boss.ui_a4_talent_scout.Tabs.MyShoutsTab;
import com.boss.ui_a4_talent_scout.Tabs.MyStreamsTab;
import com.boss.ui_a4_talent_scout.Tabs.Tab;
import com.boss.ui_a4_talent_scout.Tabs.TabAdapter;
import com.boss.ui_a4_talent_scout.Tabs.TabFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ArtistsSelected {
    private ViewPagerListener viewPagerListener;
    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this, LoginActivity.class);
        startActivityForResult(i, LoginActivity.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == LoginActivity.REQUEST_CODE) {
                setupViewPager();
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivityForResult(i, LoginActivity.REQUEST_CODE);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("unchecked")
    private void setupViewPager() {
        viewPager = findViewById(R.id.view_pager);
        tabAdapter = new TabAdapter(MainTab.class, getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        viewPager.setOffscreenPageLimit(tabAdapter.getCount());
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.dark_background));
        viewPagerListener = new ViewPagerListener(viewPager);
        viewPagerListener.onPageSelected(0);
        tabLayout.getTabAt(0).setText("My Artists");
        tabLayout.getTabAt(1).setText("My Streams");
        tabLayout.getTabAt(2).setText("My Shouts");
    }

    @Override
    public void onArtistsSelected(ArrayList<Musician> musicians) {
        MyArtistsTab myArtistsTab = (MyArtistsTab) tabAdapter.getItem(0);
        MyStreamsTab myStreamsTab = (MyStreamsTab) tabAdapter.getItem(1);
        myArtistsTab.onFragmentInteraction(musicians);
        myStreamsTab.onFragmentInteraction(musicians);
    }

    private class ViewPagerListener implements ViewPager.OnPageChangeListener, View.OnTouchListener {
        public ViewPagerListener(ViewPager viewPager) {
            viewPager.setOnTouchListener(this);
            viewPager.addOnPageChangeListener(this);
        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                Tab tab = tabAdapter.getTab(i);
                TabLayout.Tab tabLayout = MainActivity.this.tabLayout.getTabAt(i);
                TabFragment tf = (TabFragment) tabAdapter.getItem(i);
                tf.setTabVisible(i == position);
                tf.setMenuVisibility(true);
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }

        @Override public void onPageScrollStateChanged(int state) { }
        @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MyArtistsTab();
                case 1:
                    return new MyStreamsTab();
                case 2:
                    return new MyShoutsTab();
            }
            return new Fragment();
        }

        public void addFrag(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
