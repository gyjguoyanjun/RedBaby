package com.team3.baby.module.fragments_myebuy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.team3.baby.R;

/**
 * @类的用途:
 * @author:jiajianhai
 * @date:2017/5/18
 */
public class XiaoXi extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.at_xiaoxi_mebuy);
        findViewById(R.id.iv_back_xiaoxi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }



}