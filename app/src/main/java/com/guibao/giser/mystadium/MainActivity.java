package com.guibao.giser.mystadium;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.guibao.giser.mystadium.Engine.MenuEngine;

public class MainActivity extends Activity {
    TextView  tvChangdi;
    TextView  tvHuodong;
    TextView tvMsg;
    TextView tvUser;

    TextView  tvZuqiu;
    TextView  tvLanqiu;
    TextView tvYumiaoqiu;
    TextView tvBinBanqiu;
    TextView tvWanqiu;

    MenuEngine MenuEngineBottom;
    MenuEngine MenuEngineTop;
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

        tabHost.addTab(tabHost.newTabSpec("tabYimaoqiu").setIndicator("羽毛球",
                getResources().getDrawable(R.mipmap.ic_launcher)).setContent(
                R.id.view1));

        tabHost.addTab(tabHost.newTabSpec("tabZuqiu").setIndicator("足球")
                .setContent(R.id.view2));

        tabHost.addTab(tabHost.newTabSpec("tabLanqiu").setIndicator("篮球")
                .setContent(R.id.view3));
        tabHost.addTab(tabHost.newTabSpec("tabWangqiu").setIndicator("网球")
                .setContent(R.id.view4));
        tabHost.addTab(tabHost.newTabSpec("tabWangqiu").setIndicator("兵乓球")
                .setContent(R.id.view5));

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
            }
        });
    }

    void  InitBottomMenu_huodong()
    {
        // 获取TabHost对象
        TabHost tabHost2 = (TabHost) findViewById(R.id.tabhost2);
        tabHost2.setup();
        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost
        tabHost2.addTab(tabHost2.newTabSpec("tabYimaoqiu").setIndicator("羽毛球",
                getResources().getDrawable(R.mipmap.ic_launcher)).setContent(
                R.id.view12));

        tabHost2.addTab(tabHost2.newTabSpec("tabZuqiu").setIndicator("足球")
                .setContent(R.id.view22));

        tabHost2.addTab(tabHost2.newTabSpec("tabLanqiu").setIndicator("篮球")
                .setContent(R.id.view32));
        tabHost2.addTab(tabHost2.newTabSpec("tabWangqiu").setIndicator("网球")
                .setContent(R.id.view42));
        tabHost2.addTab(tabHost2.newTabSpec("tabBingpang").setIndicator("兵乓球")
                .setContent(R.id.view52));

        tabHost2.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.d("GatewayActivity", tabId);
                if (tabId.equals("tabYimaoqiu")) {
                    TextView tv = (TextView) findViewById(R.id.view12);
                    tv.setText("dkdkdkdk1111");
                }

                if (tabId.equals("tabZuqiu")) {
                    TextView tv = (TextView) findViewById(R.id.view22);
                    tv.setText("dkdkdkdk2222");
                }

                if (tabId.equals("tabLanqiu")) {
                    TextView tv = (TextView) findViewById(R.id.view32);
                    tv.setText("dkdkdkdk33333");
                }

                if (tabId.equals("tabWangqiu")) {
                    TextView tv = (TextView) findViewById(R.id.view32);
                    tv.setText("dkdkdkdk33444");
                }

                if (tabId.equals("tabBingpang")) {
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
