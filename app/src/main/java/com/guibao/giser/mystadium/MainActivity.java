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

public class MainActivity extends Activity{
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

    waterFallActivity water1;
    waterFallActivity water2;
    waterFallActivity water3;
    waterFallActivity water4;
    waterFallActivity water5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        water1 = new waterFallActivity(this,"image1",R.id.waterfall_scroll1,R.id.waterfall_container1,R.id.progressbar1,R.id.loadtext1);
        water2 = new waterFallActivity(this,"image2",R.id.waterfall_scroll2,R.id.waterfall_container2,R.id.progressbar2,R.id.loadtext2);
        water3 = new waterFallActivity(this,"image3",R.id.waterfall_scroll3,R.id.waterfall_container3,R.id.progressbar3,R.id.loadtext3);
        water4 = new waterFallActivity(this,"image4",R.id.waterfall_scroll4,R.id.waterfall_container4,R.id.progressbar4,R.id.loadtext4);
        water5 = new waterFallActivity(this,"image5",R.id.waterfall_scroll5,R.id.waterfall_container5,R.id.progressbar5,R.id.loadtext5);

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
                R.id.view1));
        tabHost.addTab(tabHost.newTabSpec("tabZuqiu").setIndicator("足球")
                .setContent(R.id.view2));
        tabHost.addTab(tabHost.newTabSpec("tabLanqiu").setIndicator("篮球")
                .setContent(R.id.view3));
        tabHost.addTab(tabHost.newTabSpec("tabWangqiu").setIndicator("网球")
                .setContent(R.id.view4));
        tabHost.addTab(tabHost.newTabSpec("tabbpqiu").setIndicator("兵乓球")
                .setContent(R.id.view5));

        water1.initView();
        water1.initImage();

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.d("GatewayActivity", tabId);
                if (tabId.equals("tabYumaoqiu")) {
                    if(!water1.IsLoaded) {
                        water1.initView();
                        water1.initImage();
                    }
                }
                if (tabId.equals("tabZuqiu")) {
                    if(!water2.IsLoaded) {
                        water2.initView();
                        water2.initImage();
                    }
                }
                if (tabId.equals("tabLanqiu")) {
                    if(!water3.IsLoaded) {
                        water3.initView();
                        water3.initImage();
                    }
                }
                if (tabId.equals("tabWangqiu")) {
                    if(!water4.IsLoaded) {
                        water4.initView();
                        water4.initImage();
                    }
                }
                if (tabId.equals("tabbpqiu")) {
                    if(!water5.IsLoaded) {
                        water5.initView();
                        water5.initImage();
                    }
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
