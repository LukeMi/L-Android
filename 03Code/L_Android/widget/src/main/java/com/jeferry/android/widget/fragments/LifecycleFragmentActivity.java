package com.jeferry.android.widget.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jeferry.android.widget.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LifecycleFragmentActivity extends AppCompatActivity {


    private TabLayout tabs;

    private ViewPager vPagers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_fragment);
        initData();
        initView();
    }

    private void initView() {
        tabs = findViewById(R.id.tabs);
        vPagers = findViewById(R.id.vPagers);
        tabs.setupWithViewPager(vPagers);
    }

    private void initData() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(AFragment.newInstance("", ""));
        fragmentList.add(BFragment.newInstance("", ""));
        fragmentList.add(CFragment.newInstance("", ""));
        fragmentList.add(DFragment.newInstance("", ""));
        fragmentList.add(EFragment.newInstance("", ""));
        fragmentList.add(FFragment.newInstance("", ""));

        List<String> titles = Arrays.asList(getResources().getStringArray(R.array.lifecycleTabs));

        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), titles, fragmentList);

        vPagers.setAdapter(mainAdapter);

        for (String s : titles) {
            TabLayout.Tab tab = tabs.newTab().setText(s);
            tabs.addTab(tab);
        }

    }


    class MainAdapter extends FragmentPagerAdapter {

        private List<String> titles;
        private List<Fragment> fragmentList;


        public MainAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragmentList) {
            super(fm);
            this.titles = titles;
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            if (fragmentList != null && fragmentList.size() > 0) {
                return fragmentList.get(position);
            }
            return null;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }


    }


}
