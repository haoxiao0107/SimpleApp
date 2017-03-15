package com.hx.simpleapp.base.inter;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.hx.simpleapp.base.adapter.BaseViewPagerAdapter;

/**
 * Created by God
 * on 2016/10/12.
 */

public interface IPagerFragment {
    BaseViewPagerAdapter getPagerAdapter();

    ViewPager getViewPager();

    Fragment getCurrent();
}
