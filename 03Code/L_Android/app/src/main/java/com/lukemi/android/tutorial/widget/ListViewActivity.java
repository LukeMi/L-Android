package com.lukemi.android.tutorial.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.bean.NHDZ_DZ_Bean;
import com.lukemi.android.tutorial.util.Logcat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * ListView学习
 * 本例子资源来自于内涵段子-->>段子模块
 * <p>
 * created bt: tubg
 * created at: 2017/4/10 11:12
 * e_mail: tbug.chen@gmail.com / tbugchen@163.com
 */
public class ListViewActivity extends AppCompatActivity {

    private String url = "http://is.snssdk.com/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-102&message_cursor=-1&am_longitude=110&am_latitude=120&am_city=%E5%8C%97%E4%BA%AC%E5%B8%82&am_loc_time=1489226058493&count=30&min_time=1489205901&screen_width=1450&do00le_col_mode=0&iid=3216590132&device_id=32613520945&ac=wifi&channel=360&aid=7&app_name=joke_essay&version_code=612&version_name=6.1.2&device_platform=android&ssmix=a&device_type=sansung&device_brand=xiaomi&os_api=28&os_version=6.10.1&uuid=326135942187625&openudid=3dg6s95rhg2a3dg5&manifest_version_code=612&resolution=1450*2800&dpi=620&update_version_code=6120";
    private ListView listView;
    private TextView title;
    private List<NHDZ_DZ_Bean.DataBeanX.DataBean> lists = new ArrayList<>();
    private LVAdapter adapter;
    private final int MSG_CALL_UI = 0x00001;
    private Handler mhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_CALL_UI:
                    //主线程更新UI
                    List<NHDZ_DZ_Bean.DataBeanX.DataBean> getLists = (List<NHDZ_DZ_Bean.DataBeanX.DataBean>) msg.obj;
                    if (getLists != null) {
                        for (int i = 0; i < getLists.size(); i++) {
                            NHDZ_DZ_Bean.DataBeanX.DataBean dataBean = getLists.get(i);
                            if (dataBean.getGroup() != null && !TextUtils.isEmpty(dataBean.getGroup().toString())) {
                                lists.add(dataBean);
                            } else {
                                continue;
                            }
                        }
                    }
                    adapter = new LVAdapter(ListViewActivity.this, lists);
                    listView.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initView();
    }

    private void initView() {
        listView = ((ListView) findViewById(R.id.listView));
        title = ((TextView) findViewById(R.id.title));
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<NHDZ_DZ_Bean.DataBeanX.DataBean> dataBeens = getList(url);
                //使用Volley注释掉
              /*  Logcat.log("LVAdapter-->>dataBeens: " + dataBeens.toString());
                Message message = mhandler.obtainMessage(MSG_CALL_UI, dataBeens);
                mhandler.sendMessage(message);*/
                Logcat.log("LVAdapter-->>dataBeens: " + dataBeens.toString());
                Message message = mhandler.obtainMessage(MSG_CALL_UI, dataBeens);
                mhandler.sendMessage(message);
            }
        }).start();


    }

    //获取集合
    public List<NHDZ_DZ_Bean.DataBeanX.DataBean> getList(String url) {
//        String s = HttpUtils.httpGetRequest_String(url);
        //Volly请求
//        String s = volleyRequest(this, url);
        //okhttp3
        String s = okHttp3Request(this, url);
        return formateList(s);
    }

    public List<NHDZ_DZ_Bean.DataBeanX.DataBean> formateList(String s) {
        Gson gson = new Gson();
        NHDZ_DZ_Bean nhdz_dz_bean = gson.fromJson(s, NHDZ_DZ_Bean.class);
        List<NHDZ_DZ_Bean.DataBeanX.DataBean> dataBeens = null;
        if (nhdz_dz_bean != null) {
            dataBeens = nhdz_dz_bean.getData().getData();
        }
        return dataBeens;
    }

    private String volleyRequest(Context context, String url) {
        String responseStr = null;
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //主线程操作
                        title.setText(response);
                        Message message = mhandler.obtainMessage(MSG_CALL_UI, formateList(response));
                        mhandler.sendMessage(message);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Logcat.log("TAG" + error.getMessage() + error);
                    }
                });
        queue.add(stringRequest);
        return null;
    }

    public String okHttp3Request(Context context, String url) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okhttp3.Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    class LVAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater mInflater;
        private List<NHDZ_DZ_Bean.DataBeanX.DataBean> dataBeens;

        public LVAdapter(Context context, List<NHDZ_DZ_Bean.DataBeanX.DataBean> dataBeens) {
            this.context = context;
            mInflater = getLayoutInflater();
            this.dataBeens = dataBeens;

        }

        @Override
        public int getCount() {
            return dataBeens != null ? dataBeens.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return dataBeens.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_listview, parent, false);
                vh = new ViewHolder();
                vh.userIV = (CircleImageView) convertView.findViewById(R.id.user_icon);
                vh.userName = (TextView) convertView.findViewById(R.id.userName);
                vh.deleteItemIV = (ImageView) convertView.findViewById(R.id.delte_item);
                vh.commentTV = (TextView) convertView.findViewById(R.id.user_content);
                vh.support = (LinearLayout) convertView.findViewById(R.id.support);
                vh.unsupport = (LinearLayout) convertView.findViewById(R.id.unsupport);
                vh.hot = (LinearLayout) convertView.findViewById(R.id.hot);
                vh.share = (LinearLayout) convertView.findViewById(R.id.share);

                vh.support_text = (TextView) convertView.findViewById(R.id.support_text);
                vh.unsupport_text = (TextView) convertView.findViewById(R.id.unsupport_text);
                vh.hot_text = (TextView) convertView.findViewById(R.id.hot_text);
                vh.share_text = (TextView) convertView.findViewById(R.id.share_text);

                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            final NHDZ_DZ_Bean.DataBeanX.DataBean dataBean = dataBeens.get(position);
            if (dataBean.getGroup() == null || TextUtils.isEmpty(dataBean.getGroup().toString())) {
                Logcat.log("------------dataBean.getGroup()----------");
            } else {
                glideLoadPic(context, dataBean.getGroup().getUser().getAvatar_url(), vh.userIV);
//                CommonUtils.picassoLoadPic(context, dataBean.getGroup().getUser().getAvatar_url(), vh.userIV);
                vh.userName.setText(dataBean.getGroup().getUser().getName());
                vh.deleteItemIV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDeleteDialog(context, dataBean.getGroup().getDislike_reason());
                        dataBeens.remove(position);
                        notifyDataSetChanged();
                    }
                });
                vh.commentTV.setText(dataBean.getGroup().getText());
                vh.support_text.setText(dataBean.getGroup().getDigg_count() + "");
                vh.unsupport_text.setText(dataBean.getGroup().getUser_repin() + "");
                vh.hot_text.setText(dataBean.getGroup().getComment_count() + "");
                vh.share_text.setText(dataBean.getGroup().getShare_count() + "");
            }

            return convertView;
        }

        /**
         * Glide加载图片
         * <p>
         * created by: tbug
         * created at: 2017/4/10 19:02
         */
        private void glideLoadPic(Context context, String url, ImageView imageView) {
            Glide
                    .with(context)//传入上下文
                    .load(url)//图片url
//                    .asBitmap()//（可选）只允许加载静态图片，不需要Glide去帮我们自动进行图片格式的判断了（gif图片就只能先是一帧）
////                        .asGif()//（可选）只允许加载动态图片，和上面的只能选一个
//                    .placeholder(R.drawable.ic_launcher)//（可选）设置默认占位图
////                    .animate(R.anim.alpha)//设置动画
//                    .error(R.drawable.ic_launcher)//（可选）设置异常占位图
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)//（可选）禁用缓存
                    .into(imageView);//传入要设置的ImageView
        }

        class ViewHolder {

            CircleImageView userIV;
            TextView userName;
            ImageView deleteItemIV;
            TextView commentTV;
            TextView support_text;
            TextView unsupport_text;
            TextView hot_text;
            TextView share_text;
            LinearLayout support;
            LinearLayout unsupport;
            LinearLayout hot;
            LinearLayout share;
        }

        int selectItem = -1;

        private void showDeleteDialog(Context context, List<NHDZ_DZ_Bean.DataBeanX.DataBean.GroupBean.DislikeReasonBean> dislike_reason) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            List<String> lists = new ArrayList<>();
            if (dislike_reason != null) {
                for (int i = 0; i < dislike_reason.size(); i++) {
                    lists.add(dislike_reason.get(i).getTitle());
                }
            }
            if (lists.size() > 0) {
                String[] titles = new String[lists.size()];
                for (int i = 0; i < dislike_reason.size(); i++) {
                    titles[i] = lists.get(i);
                }
                builder.setTitle("choose the reason why not like")
                        .setSingleChoiceItems(titles, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectItem = which;
                                //这里面的是item的循序
                            }
                        })
                        .setPositiveButton("sure", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //这里面的是Button的id
                                Logcat.log("which: setPositiveButton :" + which);
                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Logcat.log("which: setNegativeButton :" + which);
                    }
                }).create().show();
            }


        }
    }
}
