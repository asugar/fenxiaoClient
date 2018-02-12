package com.arian.distribution.modules.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;

import com.arian.distribution.R;
import com.arian.distribution.base.BaseFragment;
import com.arian.distribution.modules.home.adapter.GoodsAdapter;
import com.arian.distribution.modules.home.bean.GoodsAdapterBean;
import com.arian.distribution.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xiaoyi on 2018/2/7.
 */

public class GoodsFragment extends BaseFragment {


    @BindView(R.id.sv_goods)
    SearchView svGoods;
    @BindView(R.id.rc_goods)
    RecyclerView rcGoods;

    private GoodsAdapter goodsAdapter;
    private List<GoodsAdapterBean> beans = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods;
    }

    @Override
    protected void initEventAndData() {

        initSearchView();
        initAdapter();
        testData();
    }

    private void initSearchView() {
        // 设置搜索文本监听
        svGoods.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                ToastUtil.show(getContext(), "正在开发中，敬请期待");
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {

                } else {

                }
                return false;
            }
        });

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


}
