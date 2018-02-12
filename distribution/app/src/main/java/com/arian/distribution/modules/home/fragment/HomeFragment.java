package com.arian.distribution.modules.home.fragment;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arian.distribution.R;
import com.arian.distribution.base.BaseFragment;
import com.arian.distribution.common.banner.GlideImageLoader;
import com.arian.distribution.modules.home.adapter.GoodsAdapter;
import com.arian.distribution.modules.home.bean.GoodsAdapterBean;
import com.arian.distribution.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiaoyi on 2018/2/7.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.banner_home)
    Banner bannerHome;
    @BindView(R.id.rc_goods)
    RecyclerView rcGoods;

    private GoodsAdapter goodsAdapter;
    private List<GoodsAdapterBean> beans = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEventAndData() {
        initBanner();

        initAdapter();

        testData();
    }

    private void testData() {
        for (int i = 0; i < 10; i++) {
            GoodsAdapterBean goodsAdapterBean = new GoodsAdapterBean();
            goodsAdapterBean.setGoodCount(i + "");
            goodsAdapterBean.setGoodName("商品名" + i);
            goodsAdapterBean.setGoodPrice((i >> 1) + "i");
            goodsAdapterBean.setGoodIcon(R.mipmap.default_ask + "");
            beans.add(goodsAdapterBean);
        }
        goodsAdapter.notifyDataSetChanged();
        nestedScrollView.smoothScrollTo(0, 20);
    }

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcGoods.setLayoutManager(linearLayoutManager);
        rcGoods.setHasFixedSize(true);
        rcGoods.setNestedScrollingEnabled(false);

        goodsAdapter = new GoodsAdapter(getContext(), beans);
        rcGoods.setAdapter(goodsAdapter);
        goodsAdapter.setOnItemClickListener(new GoodsAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // 跳转询价详情页面
//                Navigate.startMineEnquiryDetailActivity(getContext(), EnquiryListBeen.get(position).getId());
            }
        });
    }

    // 初始化banner
    private void initBanner() {
        List images = new ArrayList();
        List titles = new ArrayList();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                images.add("http://pic.qiantucdn.com/58pic/19/17/63/56837b8dd47fd_1024.jpg");
                titles.add("奥迪");
            } else {
                images.add("http://img5.niutuku.com/phone/1212/0404/0404-niutuku.com-16133.jpg");
                titles.add("奥立安");
            }

        }
        bannerHome.setImages(images);
        bannerHome.setBannerTitles(titles);
        //设置图片加载器
        bannerHome.setImageLoader(new GlideImageLoader());
        bannerHome.setIndicatorGravity(BannerConfig.RIGHT);
        bannerHome.setOnBannerListener(bannerListener);
        bannerHome.start();
        bannerHome.setOnBannerListener(bannerListener);
        bannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
    }

    private OnBannerListener bannerListener = new OnBannerListener() {
        @Override
        public void OnBannerClick(int position) {
            // 根据返回的position，进行相应的业务跳转
            ToastUtil.show(getContext(), "banner click " + position);
        }

    };

    @OnClick({R.id.tv_goods_manager, R.id.tv_user_manager})
    public void onManagerClick(View v) {
        switch (v.getId()) {
            case R.id.tv_goods_manager:
                ToastUtil.show(getContext(), "正在开发中，敬请期待");
                break;
            case R.id.tv_user_manager:
                ToastUtil.show(getContext(), "正在开发中，敬请期待");
                break;
        }

    }


}
