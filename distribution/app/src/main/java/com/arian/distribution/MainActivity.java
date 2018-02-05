package com.arian.distribution;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.arian.distribution.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.id_drawer_layout)
    DrawerLayout idDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

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
    }
}
