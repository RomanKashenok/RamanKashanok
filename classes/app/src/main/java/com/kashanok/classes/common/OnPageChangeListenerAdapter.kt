package com.kashanok.classes.common

import android.support.v4.view.ViewPager

interface OnPageChangeListenerAdapter : ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {}
}