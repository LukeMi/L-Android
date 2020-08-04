package com.jeferry.android.widget.wechat.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.jeferry.android.widget.R;
import com.jeferry.android.widget.wechat.fragment.WeChatContactFragment;
import com.jeferry.android.widget.wechat.fragment.WeChatDiscoverFragment;
import com.jeferry.android.widget.wechat.fragment.WeChatMSGFragment;
import com.jeferry.android.widget.wechat.fragment.WeChatMineFragment;
import com.lukemi.android.common.config.ARouterPath;

import java.util.ArrayList;
import java.util.List;


/**
 * 仿照微信主界面
 */
@Route(path = ARouterPath.GROUP_WIDGET + ARouterPath.WE_CHAT_MAIN)
public class WeChatMainActivity extends AppCompatActivity {

    private ViewPager mainViewPager;
    private TabLayout navigateTabLayout;
    private MainAdapter mainAdapter;
    private String[] titles = new String[]{"微信", "通讯录", "发现", "我"};
    private int[] drawableIDs = new int[]{R.drawable.selector_wechat_main_message
            , R.drawable.selector_wechat_main_contact
            , R.drawable.selector_wechat_main_discover
            , R.drawable.selector_wechat_main_mine};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat_main);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wechat_main, menu);
        menu.add(0, Menu.FIRST + 2, 2, "个人信息").setIcon(android.R.drawable.ic_menu_send);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_search) {
            Toast.makeText(this, "R.id.action_search", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.action_group_chat) {
            Toast.makeText(this, "R.id.action_group_chat", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.action_add_friend) {
            Toast.makeText(this, "R.id.action_add_friend", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.action_scan) {
            Toast.makeText(this, "R.id.action_scan", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.action_feed) {
            Toast.makeText(this, "R.id.action_feed", Toast.LENGTH_SHORT).show();
        } else if (itemId == Menu.FIRST + 2) {
            Toast.makeText(this, "Menu.FIRST + 2", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mainViewPager = findViewById(R.id.mainViewPager);
        navigateTabLayout = findViewById(R.id.bottomNavigationBar);
        initMainViewPager();
        initNavigateTabLayout();
    }

    /**
     * 初始化底部导航栏
     */
    private void initNavigateTabLayout() {

   /*     navigateTabLayout.addTab(navigateTabLayout.newTab().setIcon(R.drawable.selector_wechat_main_message).setText(titles[0]));
        navigateTabLayout.addTab(navigateTabLayout.newTab().setIcon(R.drawable.selector_wechat_main_contact).setText(titles[1]));
        navigateTabLayout.addTab(navigateTabLayout.newTab().setIcon(R.drawable.selector_wechat_main_discover).setText(titles[2]));
        navigateTabLayout.addTab(navigateTabLayout.newTab().setIcon(R.drawable.selector_wechat_main_mine).setText(titles[3]));
*/
        navigateTabLayout.setupWithViewPager(mainViewPager);////将TabLayout和ViewPager关联起来(问题导致icon不能显示)。
//setupWithViewPager后遗症解决
//方法一
        for (int i = 0; i < navigateTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = navigateTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mainAdapter.getTabView(i));
            }
            if (i == 0) {
                navigateTabLayout.getTabAt(0).getCustomView().setSelected(true);
            }
        }

    /*     //方法二
    for (int i = 0; i < navigateTabLayout.getChildCount(); i++) {
            navigateTabLayout.getTabAt(i).setIcon(drawableIDs[i]).setText(titles[i]);//默认Icon 与 title GAP 8dp 不可更改
            //
        }*/
    }

    /**
     * 初始化ViewPager
     */
    private void initMainViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
     /*   fragmentList.add(SubLazyLoadFragment.newInstance("", ""));
        fragmentList.add(SubLazyLoadFragment.newInstance("", ""));
        fragmentList.add(SubLazyLoadFragment.newInstance("", ""));
        fragmentList.add(SubLazyLoadFragment.newInstance("", ""));*/

        fragmentList.add(WeChatMSGFragment.newInstance("", ""));
        fragmentList.add(WeChatContactFragment.newInstance("", ""));
        fragmentList.add(WeChatDiscoverFragment.newInstance("", ""));
        fragmentList.add(WeChatMineFragment.newInstance("", ""));
        mainAdapter = new MainAdapter(getSupportFragmentManager(), this, titles, drawableIDs, fragmentList);
        mainViewPager.setAdapter(mainAdapter);

    }


    class MainAdapter extends FragmentPagerAdapter {

        private String[] titles;
        private List<Fragment> fragmentList;
        private Context context;
        private LayoutInflater mInflater;


        public MainAdapter(FragmentManager fm, Context context, String[] itemNames, int[] drawableIDs, List<Fragment> fragmentList) {
            super(fm);
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.titles = itemNames;
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

        public View getTabView(int position) {
            View view = View.inflate(context, R.layout.item_wechat_main_navigation, null);
            ImageView imageView = view.findViewById(R.id.icon);
            TextView textView = view.findViewById(R.id.title);
            imageView.setImageDrawable(getResources().getDrawable(drawableIDs[position]));
            textView.setText(titles[position]);
            return view;
        }

    /*    @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }*/


    }

}
