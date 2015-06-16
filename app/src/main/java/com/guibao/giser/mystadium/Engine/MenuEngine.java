package com.guibao.giser.mystadium.Engine;

import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by giser on 2015/6/15.
 */
public class MenuEngine {
    ArrayList<TextView> ListTv;
    public MenuEngine()
    {
        this.ListTv = new ArrayList<>();
    }

    public MenuEngine(RelativeLayout rl)
    {
        this.ListTv = new ArrayList<>();

        int len = rl.getChildCount();
        for(int i = 0;i<len;i++)
        {
            TextView tv = (TextView)rl.getChildAt(i);
            this.ListTv.add(tv);
        }
    }

    public  void Append(TextView tv)
    {
        int len = this.ListTv.size();
        Boolean isB = false;
       for(int i = 0;i<len;i++)
       {
           String str1 = String.valueOf(tv.getText());
           String str2 = String.valueOf(this.ListTv.get(i).getText());
           if(str1.equals(str2))
            {
                isB = true;
                break;
            }
       }
        if(isB) return;;

        this.ListTv.add(tv);
    }

    public void  Action(TextView tv)
    {
        int len = this.ListTv.size();
        for(int i = 0;i<len;i++)
        {
            String str1 = String.valueOf(tv.getText());
            String str2 = String.valueOf(this.ListTv.get(i).getText());
            if(str1.equals(str2)) {
                this.ListTv.get(i).setSelected(true);
            }
            else   this.ListTv.get(i).setSelected(false);
        }
    }
}
