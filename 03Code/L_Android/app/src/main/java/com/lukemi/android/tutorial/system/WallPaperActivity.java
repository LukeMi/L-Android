package com.lukemi.android.tutorial.system;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lukemi.android.tutorial.MainActivity;
import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.LauncherActivity;
import com.lukemi.android.common.ShowBigPicClass;
import com.lukemi.android.tutorial.util.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置墙纸<br/>
 * 功能点:ViewPager设置最后一页向做拖拽即可跳转
 * <p>
 * created bt: tubg
 * created at: 2017/4/9 16:03
 * e_mail: tbug.chen@gmail.com / tbugchen@163.com
 */
public class WallPaperActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView showIV;
    private ViewPager viewPager;
    private int sourceId;
    private List<Integer> list = new ArrayList<>();
    private boolean misScrolled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.tutorial.R.layout.activity_wall_paper);
        initView();
    }

    /**
     * 初始化控件
     * <p>
     * created by: tbug
     * created at: 2017/4/9 14:11
     */
    private void initView() {
        findViewById(com.lukemi.android.tutorial.R.id.setWallPaper).setOnClickListener(this);

        list.add(com.lukemi.android.tutorial.R.drawable.wallpaper1);
        list.add(com.lukemi.android.tutorial.R.drawable.wallpaper2);
        list.add(com.lukemi.android.tutorial.R.drawable.wallpaper3);
        list.add(com.lukemi.android.tutorial.R.drawable.wallpaper4);

        final MPagerAdapder mPagerAdapder = new MPagerAdapder(this, list);
        viewPager = ((ViewPager) findViewById(com.lukemi.android.tutorial.R.id.viewPager));
//        viewPager.setAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
        viewPager.setAdapter(mPagerAdapder);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                sourceId = position;
                Logcat.log("sourceId : " + sourceId + "  " + list.get(position));
                showIV.setImageResource(list.get(position));
                if (position == list.size() - 1) {
                    Logcat.log("position : " + position);
                    viewPager.setOnClickListener(WallPaperActivity.this);
                } else {
                    viewPager.setOnClickListener(null);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //最后一页向右拉跳转
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        Logcat.log("onPageScrollStateChanged: -->>ViewPager.SCROLL_STATE_DRAGGING" + " ;misScrolled: " + misScrolled);
                        misScrolled = true;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        Logcat.log("onPageScrollStateChanged: -->>ViewPager.SCROLL_STATE_SETTLING" + " ;misScrolled: " + misScrolled);
                        misScrolled = false;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        Logcat.log("onPageScrollStateChanged: -->>ViewPager.SCROLL_STATE_IDLE" + " ;misScrolled: " + misScrolled);
                        if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && misScrolled) {
                            go2Launch();
                            WallPaperActivity.this.finish();
                        }
                        misScrolled = false;
                        break;
                    default:
                        break;
                }
            }
        });
        showIV = findViewById(com.lukemi.android.tutorial.R.id.showPic);
        showIV.setImageResource(list.get(0));//设置默认值
    }

    private void go2Launch() {
        Intent intent = new Intent(WallPaperActivity.this, LauncherActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.lukemi.android.tutorial.R.id.setWallPaper:
                setWallPaper(this, BitmapUtils.drawable2Bitmap(showIV.getDrawable()));
                break;
            case com.lukemi.android.tutorial.R.id.viewPager:
                Logcat.log("position : " + sourceId);
                startActivity(new Intent(WallPaperActivity.this, MainActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 设置壁纸
     * <p>
     * created by: tbug
     * created at: 2017/4/9 13:49
     */
    private void setWallPaper(Context context, Bitmap bitmap) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        try {
            wallpaperManager.setBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 适配器Viewpager的适配器
     * <p>
     * created bt: tubg
     * created at: 2017/4/9 14:12
     * e_mail: tbug.chen@gmail.com / tbugchen@163.com
     */
    class MPagerAdapder extends PagerAdapter {

        private Context context;
        private List<Integer> list;

        public MPagerAdapder(Context context, List<Integer> list) {

            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView view = new ImageView(context);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
                layoutParams.height = context.getResources().getDisplayMetrics().heightPixels;
                view.setLayoutParams(layoutParams);
            }
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageResource(list.get(position));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowBigPicClass sbc = new ShowBigPicClass(WallPaperActivity.this, list.get(position));
                    sbc.showDetailPhoto();
                }
            });
            if (container != null) {
                container.addView(view);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            if (container != null) {
                container.removeView((View) object);
            }
        }
    }

}
