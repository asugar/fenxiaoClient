package com.arian.distribution.modules.home.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arian.distribution.R;
import com.arian.distribution.base.BaseActivity;
import com.arian.distribution.modules.home.fragment.GoodsFragment;
import com.arian.distribution.modules.home.fragment.HomeFragment;
import com.arian.distribution.modules.home.fragment.OrderFragment;
import com.arian.distribution.modules.home.fragment.UserFragment;
import com.arian.distribution.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.id_drawer_layout)
    DrawerLayout idDrawerLayout;
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.ll_user)
    LinearLayout llUser;
    @BindView(R.id.iv_goods)
    ImageView ivGoods;
    @BindView(R.id.tv_goods)
    TextView tvGoods;
    @BindView(R.id.ll_goods)
    LinearLayout llGoods;
    @BindView(R.id.iv_order)
    ImageView ivOrder;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.ll_order)
    LinearLayout llOrder;

    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager mFragmentManager = null;
    /**
     * 首页
     */
    private HomeFragment mHomeFragment = null;

    /**
     * 用户
     */
    private UserFragment mUserFragment = null;

    /**
     * 商品
     */
    private GoodsFragment mGoodsFragment = null;

    /**
     * 订单
     */
    private OrderFragment mOrderFragment = null;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "分销系统");

        mDrawerToggle = new ActionBarDrawerToggle(this, idDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        idDrawerLayout.addDrawerListener(mDrawerToggle);

        navView.setNavigationItemSelectedListener(navigationItemSelectedListener);

        initFragment();

    }

    private void initFragment() {
        mFragmentManager = this.getSupportFragmentManager();

        // 主页
        llHome.setOnClickListener(view -> setTabSelection(R.id.ll_home));

        // 用户
        llUser.setOnClickListener(view -> setTabSelection(R.id.ll_user));

        // 商品
        llGoods.setOnClickListener(view -> setTabSelection(R.id.ll_goods));

        // 订单
        llOrder.setOnClickListener(view -> setTabSelection(R.id.ll_order));


        setTabSelection(R.id.ll_home);

    }

    FragmentTransaction tmpTransaction = null;
    private int mSelector = 0;

    private void setTabSelection(int aIdx) {
        if (mSelector == aIdx) {
            return;
        }
        mSelector = aIdx;

        tmpTransaction = mFragmentManager.beginTransaction();
        switch (aIdx) {
            // 首页
            case R.id.ll_home:
                changeTabState();
                ivHome.setImageResource(R.mipmap.icon_home_selected);
                tvHome.setTextColor(getResources().getColorStateList(R.color.home_text_selected_color));
                hindFragment();
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    tmpTransaction.add(R.id.fl_main, mHomeFragment);
                }
                tmpTransaction.show(mHomeFragment);
                break;

            // 用户
            case R.id.ll_user:
                //判断是否登录   没有登录去登陆
//                if (!noLoginToLoginActivity()) {
//                    return;
//                }
                changeTabState();
                ivUser.setImageResource(R.mipmap.icon_user_selected);
                tvUser.setTextColor(getResources().getColorStateList(R.color.home_text_selected_color));
                hindFragment();
                if (mUserFragment == null) {
                    mUserFragment = new UserFragment();
                    tmpTransaction.add(R.id.fl_main, mUserFragment);
                }
                tmpTransaction.show(mUserFragment);
                break;

            // 商品
            case R.id.ll_goods:
                //判断是否登录   没有登录去登陆
//                if (!noLoginToLoginActivity()) {
//                    return;
//                }
                changeTabState();
                ivGoods.setImageResource(R.mipmap.icon_good_selected);
                tvGoods.setTextColor(this.getResources().getColorStateList(R.color.home_text_selected_color));
                hindFragment();
                mGoodsFragment = new GoodsFragment();
                tmpTransaction.add(R.id.fl_main, mGoodsFragment);
                tmpTransaction.show(mGoodsFragment);
                break;
            // 订单
            case R.id.ll_order:
                //判断是否登录   没有登录去登陆
//                if (!noLoginToLoginActivity()) {
//                    return;
//                }
                changeTabState();
                ivOrder.setImageResource(R.mipmap.icon_order_selected);
                tvOrder.setTextColor(this.getResources().getColorStateList(R.color.home_text_selected_color));
                hindFragment();
                if (mOrderFragment == null) {
                    mOrderFragment = new OrderFragment();
                    tmpTransaction.add(R.id.fl_main, mOrderFragment);
                }

                tmpTransaction.show(mOrderFragment);
                break;
        }
        tmpTransaction.commit();
    }

    /**
     * 切换选项卡颜色
     */
    private void changeTabState() {

        ivHome.setImageResource(R.mipmap.icon_home_nomal);
        tvHome.setTextColor(getResources().getColor(android.R.color.secondary_text_light));

        ivUser.setImageResource(R.mipmap.icon_user_nomal);
        tvUser.setTextColor(getResources().getColor(android.R.color.secondary_text_light));

        ivGoods.setImageResource(R.mipmap.icon_good_nomal);
        tvGoods.setTextColor(this.getResources().getColorStateList(android.R.color.secondary_text_light));

        ivOrder.setImageResource(R.mipmap.icon_order_nomal);
        tvOrder.setTextColor(this.getResources().getColorStateList(android.R.color.secondary_text_light));
    }

    /**
     * 隐藏其他fragment
     */
    private void hindFragment() {
        if (mHomeFragment != null) {
            tmpTransaction.hide(mHomeFragment);
        }
        if (mUserFragment != null) {
            tmpTransaction.hide(mUserFragment);
        }
        if (mGoodsFragment != null) {
            tmpTransaction.hide(mGoodsFragment);
        }
        if (mOrderFragment != null) {
            tmpTransaction.hide(mOrderFragment);
        }
    }

    NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_change_password:
                    ToastUtil.show(getContext(), "changePassword click");
                    return true;
                case R.id.action_version:
                    ToastUtil.show(getContext(), "version click");
                    return true;
                case R.id.action_logout:
                    ToastUtil.show(getContext(), "logout click");
                    return true;
            }
            return false;
        }
    };


    @OnClick({R.id.ll_home, R.id.ll_user, R.id.ll_goods, R.id.ll_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                break;
            case R.id.ll_user:
                break;
            case R.id.ll_goods:
                break;
            case R.id.ll_order:
                break;
        }
    }
}
