package com.team3.baby.module.fragments_home;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.team3.baby.R;
import com.team3.baby.module.fragments_home.activity.HomeSeekActivity;
import com.team3.baby.module.fragments_home.adapter.HomeAdapter;
import com.team3.baby.module.fragments_home.bean.TitleBean;
import com.team3.baby.module.fragments_home.fragments.ItemHomeFragment;
import com.team3.baby.module.fragments_home.fragments.ItemHomeFragmentOrder;
import com.team3.baby.module.fragments_home.fragments.ItemHomeFragmentTitle;
import com.team3.baby.module.fragments_home.url.Url;
import com.team3.baby.module.fragments_myebuy.XiaoXi;
import com.team3.baby.utils.OkUtils;
import com.team3.baby.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tianjieyu on 2017/5/17.
 */

public class HomeFragment extends Fragment {
    @BindView(R.id.tab_top_home_fragment)
    TabLayout mTabTopHomeFragment;
    @BindView(R.id.vp_home_fragment)
    ViewPager mVpHomeFragment;
    @BindView(R.id.image_scan_include)
    ImageView mImageScanInclude;
    @BindView(R.id.linear_inquire_include)
    LinearLayout mLinearInquireInclude;
    @BindView(R.id.image_mes_include)
    ImageView mImageMesInclude;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        OkUtils.getEnqueue(Url.TITLE, null, new OkUtils.MyCallback() {
            @Override
            public void onSuccess(String result) {

                List<Fragment> listFram = new ArrayList<Fragment>();
                List<String> listStr = new ArrayList<String>();
                Gson gson = new Gson();
                TitleBean titleBean = gson.fromJson(result, TitleBean.class);
                List<TitleBean.DataBean> data = titleBean.getData();
                data.get(0).getTag().remove(4);
                data.get(0).getTag().remove(5);
                ItemHomeFragment itemHomeFragment1 = new ItemHomeFragment().newInstance(Url.TITLE);
                listFram.add(itemHomeFragment1);
                listStr.add("上新");
                ItemHomeFragmentOrder itemHomeFragmentOrder = new ItemHomeFragmentOrder().newInstance(Url.ORDER);
                listFram.add(itemHomeFragmentOrder);
                listStr.add("订单");
                for (int i = 0; i < data.get(0).getTag().size(); i++) {
                    ItemHomeFragmentTitle itemHomeFragmentTitle = new ItemHomeFragmentTitle().newInstance(data.get(0).getTag().get(i).getElementDesc());
                    listFram.add(itemHomeFragmentTitle);
                    listStr.add(data.get(0).getTag().get(i).getElementName());

                }

                mTabTopHomeFragment.setTabMode(TabLayout.MODE_SCROLLABLE);

                HomeAdapter adapter = new HomeAdapter(getFragmentManager(), listFram, listStr);
                mVpHomeFragment.setAdapter(adapter);//给ViewPager设置适配器
                mTabTopHomeFragment.setupWithViewPager(mVpHomeFragment);//将TabLayout和ViewPager关联起来。
                mTabTopHomeFragment.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器
                mLinearInquireInclude.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), HomeSeekActivity.class);
                        startActivity(intent);
                    }
                });
                mImageMesInclude.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(getActivity(), XiaoXi.class);
                        startActivity(intent);

                    }
                });
                mImageScanInclude.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Acp.getInstance(getActivity() ).request(new AcpOptions.Builder()
                                        .setPermissions(Manifest.permission.CAMERA
                                                , Manifest.permission.READ_PHONE_STATE
                                                , Manifest.permission.WRITE_EXTERNAL_STORAGE


                        )

                /*以下为自定义提示语、按钮文字
                .setDeniedMessage()
                .setDeniedCloseBtn()
                .setDeniedSettingBtn()
                .setRationalMessage()
                .setRationalBtn()*/
                                        .build(),
                                new AcpListener() {
                                    @Override
                                    public void onGranted() {
                                        startActivity(new Intent(getActivity(), CaptureActivity.class));

                                    }

                                    @Override
                                    public void onDenied(List<String> permissions) {

                                    }
                                });

                    }
                });
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }
}
