package com.team3.baby.module.fragments_myebuy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.team3.baby.R;

/**
 * @类的用途:我的个人信息
 * @author:jiajianhai
 * @date:2017/5/18
 */
public class MyMessage extends Activity {
    //**

    //   setContentView(R.layout.mymessage_layout_mebuy);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymessage_layout_mebuy);
        findViewById(R.id.iv_back_mymessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}