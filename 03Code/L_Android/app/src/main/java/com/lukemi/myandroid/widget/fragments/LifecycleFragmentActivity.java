package com.lukemi.myandroid.widget.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.lukemi.myandroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LifecycleFragmentActivity extends AppCompatActivity {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.vPagers)
    ViewPager vPagers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_fragment);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
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
