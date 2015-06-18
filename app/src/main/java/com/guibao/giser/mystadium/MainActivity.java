package com.guibao.giser.mystadium;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.guibao.giser.mystadium.Engine.MenuEngine;
import com.guibao.giser.mystadium.waterFall.ImageDownLoadAsyncTask;
import com.guibao.giser.mystadium.waterFall.LazyScrollView;
import com.guibao.giser.mystadium.waterFall.waterFallActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity  implements
        LazyScrollView.OnScrollListener {
    TextView  tvChangdi;
    TextView  tvHuodong;
    TextView tvMsg;
    TextView tvUser;

    TextView tvZuqiu;
    TextView tvLanqiu;
    TextView tvYumiaoqiu;
    TextView tvBinBanqiu;
    TextView tvWanqiu;

    MenuEngine MenuEngineBottom;
    MenuEngine MenuEngineTop;

    //以下为图片瀑布流
    private LazyScrollView lazyScrollView;
    private LinearLayout waterfall_container;
    private ArrayList<LinearLayout> linearLayouts;// 鍒楀竷灞?
    private LinearLayout progressbar;// 杩涘害鏉?
    private TextView loadtext;// 搴曢儴鍔犺浇view

    private AssetManager assetManager;

    private List<String> image_filenames; // 鍥剧墖闆嗗悎
    private ImageDownLoadAsyncTask asyncTask;

    private int current_page = 0;// 椤电爜
    private int count = 20;// 姣忛〉鏄剧ず鐨勪釜鏁?	private int column = 4;// 鏄剧ず鍒楁暟s

    private int item_width;// 姣忎竴涓猧tem鐨勫搴?	private final String file = "images";
    int column =1;
    String file= "images";
    /***
     * init view
     */
    public void initView() {
        //setContentView(R.layout.);
        lazyScrollView = (LazyScrollView) findViewById(R.id.waterfall_scroll);
        lazyScrollView.getView();
        lazyScrollView.setOnScrollListener(this);
        waterfall_container = (LinearLayout) findViewById(R.id.waterfall_container);
        progressbar = (LinearLayout) findViewById(R.id.progressbar);
        loadtext = (TextView) findViewById(R.id.loadtext);

        item_width = getWindowManager().getDefaultDisplay().getWidth();
        linearLayouts = new ArrayList<LinearLayout>();

        // 娣诲姞涓夊垪鍒皐aterfall_container

        for (int i = 0; i < column; i++) {
            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams itemParam = new LinearLayout.LayoutParams(
                    item_width, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(itemParam);
            linearLayouts.add(layout);
            waterfall_container.addView(layout);
        }
    }

    /***
     * 鍔犺浇鏇村
     *
     * @param current_page
     * @param count
     */
    private void addImage(int current_page, int count) {
        int j = 0;
        int imagecount = image_filenames.size();
        for (int i = current_page * count; i < count * (current_page + 1)
                && i < imagecount; i++) {
            addBitMapToImage(image_filenames.get(i), j, i);
            j++;
            if (j >= column)
                j = 0;
        }
    }

    private void addBitMapToImage(String imageName, int j, int i) {
        ImageView imageView = getImageview(imageName);
        asyncTask = new ImageDownLoadAsyncTask(this, imageName, imageView,
                item_width);

        asyncTask.setProgressbar(progressbar);
        asyncTask.setLoadtext(loadtext);
        asyncTask.execute();

        imageView.setTag(i);
        // 娣诲姞鐩稿簲view
        linearLayouts.get(j).addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "鎮ㄧ偣鍑讳簡" + v.getTag() + "涓狪tem", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    /***
     * 鑾峰彇imageview
     *
     * @param imageName
     * @return
     */
    public ImageView getImageview(String imageName) {
        BitmapFactory.Options options = getBitmapBounds(imageName);
        // 鍒涘缓鏄剧ず鍥剧墖鐨勫璞?
        ImageView imageView = new ImageView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.FILL_PARENT);
        imageView.setLayoutParams(layoutParams);
        //
        imageView.setMinimumHeight(options.outHeight);
        imageView.setMinimumWidth(options.outWidth);
        imageView.setPadding(2, 0, 2, 2);
        imageView.setBackgroundResource(R.drawable.image_border);
        if (options != null)
            options = null;
        return imageView;
    }

    /***
     *
     * 鑾峰彇鐩稿簲鍥剧墖鐨?BitmapFactory.Options
     */
    public BitmapFactory.Options getBitmapBounds(String imageName) {
        int h, w;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;// 鍙繑鍥瀊itmap鐨勫ぇ灏忥紝鍙互鍑忓皯鍐呭瓨浣跨敤锛岄槻姝OM.
        InputStream is = null;
        try {
            is = assetManager.open(file + "/" + imageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BitmapFactory.decodeStream(is, null, options);
        return options;
    }

    @Override
    public void onBottom() {
        addImage(++current_page, count);

    }

    @Override
    public void onTop() {

    }

    @Override
    public void onScroll() {

    }
   //**************end瀑布流*************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        InitTopMenu();
        InitBottomMenu();

        WindowManager wm = (WindowManager)MainActivity.this.getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Log.d("aa",String.valueOf(width));
        Log.d("aa",String.valueOf(height));
    }

    void InitTopMenu()
    {
        InitTopMenu_cangdi();
        InitBottomMenu_huodong();
    }

    void  InitTopMenu_cangdi()
    {
        // 获取TabHost对象
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("tabYumaoqiu").setIndicator("羽毛球",
                getResources().getDrawable(R.mipmap.ic_launcher)).setContent(
                R.id.view5));

        tabHost.addTab(tabHost.newTabSpec("tabZuqiu").setIndicator("足球")
                .setContent(R.id.view2));

        tabHost.addTab(tabHost.newTabSpec("tabLanqiu").setIndicator("篮球")
                .setContent(R.id.view3));
        tabHost.addTab(tabHost.newTabSpec("tabWangqiu").setIndicator("网球")
                .setContent(R.id.view4));
        tabHost.addTab(tabHost.newTabSpec("tabbpqiu").setIndicator("兵乓球")
                .setContent(R.id.view1));

        initView();
        assetManager = MainActivity.this.getAssets();
        // 获取显示图片宽度
        // int Image_width = (getWindowManager().getDefaultDisplay().getWidth() - 4) / 3;
        try {
            image_filenames = Arrays.asList(assetManager.list(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 绗竴娆″姞杞?
        addImage(current_page, count);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.d("GatewayActivity", tabId);
                if (tabId.equals("tab1")) {
                    TextView tv = (TextView) findViewById(R.id.view1);
                    tv.setText("dkdkdkdk1111");
                }

                if (tabId.equals("tab2")) {
                    TextView tv = (TextView) findViewById(R.id.view2);
                    tv.setText("dkdkdkdk2222");
                }

                if (tabId.equals("tab3")) {
                    TextView tv = (TextView) findViewById(R.id.view3);
                    tv.setText("dkdkdkdk33333");
                }
                if (tabId.equals("tab3")) {
                    TextView tv = (TextView) findViewById(R.id.view3);
                    tv.setText("dkdkdkdk33333");
                }
                if (tabId.equals("tabYumaoqiu")) {
//                    initView();
//                    assetManager = MainActivity.this.getAssets();
//                    // 获取显示图片宽度
//                    // int Image_width = (getWindowManager().getDefaultDisplay().getWidth() - 4) / 3;
//                    try {
//                        image_filenames = Arrays.asList(assetManager.list(file));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    // 绗竴娆″姞杞?
//                    addImage(current_page, count);
                }
            }
        });
    }

    void  InitBottomMenu_huodong()
    {
        // 获取TabHost对象
        TabHost tabHost2 = (TabHost) findViewById(R.id.tabhost2);
        tabHost2.setup();
        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost
        tabHost2.addTab(tabHost2.newTabSpec("tabYimaoqiu2").setIndicator("羽毛球1",
                getResources().getDrawable(R.mipmap.ic_launcher)).setContent(
                R.id.view12));

        tabHost2.addTab(tabHost2.newTabSpec("tabZuqiu2").setIndicator("足球1")
                .setContent(R.id.view22));

        tabHost2.addTab(tabHost2.newTabSpec("tabLanqiu2").setIndicator("篮球1")
                .setContent(R.id.view32));
        tabHost2.addTab(tabHost2.newTabSpec("tabWangqiu2").setIndicator("网球1")
                .setContent(R.id.view42));
        tabHost2.addTab(tabHost2.newTabSpec("tabBingpang2").setIndicator("兵乓球1")
                .setContent(R.id.view52));

        tabHost2.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.d("GatewayActivity", tabId);
                if (tabId.equals("tabYimaoqiu2")) {
                    TextView tv = (TextView) findViewById(R.id.view12);
                    tv.setText("dkdkdkdk1111");
                }

                if (tabId.equals("tabZuqiu2")) {
                    TextView tv = (TextView) findViewById(R.id.view22);
                    tv.setText("dkdkdkdk2222");
                }

                if (tabId.equals("tabLanqiu2")) {
                    TextView tv = (TextView) findViewById(R.id.view32);
                    tv.setText("dkdkdkdk33333");
//                    Intent intent = new Intent(MainActivity.this,waterFallActivity.class);
//                    startActivity(intent);
                }

                if (tabId.equals("tabWangqiu2")) {
                    TextView tv = (TextView) findViewById(R.id.view32);
                    tv.setText("dkdkdkdk33444");
                }

                if (tabId.equals("tabBingpang2")) {
                    TextView tv = (TextView) findViewById(R.id.view32);
                    tv.setText("dkdkdkdk3555");
                }
            }
        });
    }

    void  InitBottomMenu()
    {
        RelativeLayout r = (RelativeLayout)findViewById(R.id.RelativeLayoutBom);
        this.MenuEngineBottom = new MenuEngine(r);

        this.tvChangdi = (TextView)findViewById(R.id.changdi);
        this.tvChangdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuEngineBottom.Action(tvChangdi);
                TabHost ll = (TabHost)findViewById(R.id.tabhost);
                ll.setVisibility(View.VISIBLE);

                TabHost ll0 = (TabHost)findViewById(R.id.tabhost2);
                ll0.setVisibility(View.GONE);
            }
        });

        MenuEngineBottom.Action(tvChangdi);

        this.tvHuodong = (TextView)findViewById(R.id.huodong);
        this.tvHuodong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuEngineBottom.Action(tvHuodong);
                TabHost ll = (TabHost)findViewById(R.id.tabhost);
                ll.setVisibility(View.GONE);

                TabHost ll0 = (TabHost)findViewById(R.id.tabhost2);
                ll0.setVisibility(View.VISIBLE);
            }
        });

        this.tvMsg = (TextView)findViewById(R.id.tvMsg);
        this.tvMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuEngineBottom.Action(tvMsg);
            }
        });

        this.tvUser = (TextView)findViewById(R.id.tvUser);
        this.tvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuEngineBottom.Action(tvUser);
            }
        });
    }
}
