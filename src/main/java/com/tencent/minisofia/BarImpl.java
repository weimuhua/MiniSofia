
package com.tencent.minisofia;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;


class BarImpl implements Bar {

    private Activity mActivity;
    private View mStatusView;

    BarImpl(Activity activity) {
        mActivity = activity;

        replaceContentView();
        setContentViewFitSystemWindow(true);
        SofiaUtils.invasionStatusBar(mActivity);
    }

    private void setContentViewFitSystemWindow(boolean value) {
        Window window = mActivity.getWindow();
        ViewGroup contentView = window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
        View rootView = contentView.getChildAt(0);
        if (rootView != null) {
            rootView.setFitsSystemWindows(value);
        }
    }

    private void replaceContentView() {
        Window window = mActivity.getWindow();
        ViewGroup container = (ViewGroup) window.getDecorView();
        View statusBarView = container.findViewById(R.id.statusbar_view);
        if (statusBarView == null) {
            statusBarView = new View(container.getContext());
            statusBarView.setId(R.id.statusbar_view);
            mStatusView = statusBarView;
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, SofiaUtils.getStatusBarHeight(container.getContext()));
            container.addView(statusBarView, lp);
        } else {
            mStatusView = statusBarView;
        }
    }

    @Override
    public Bar statusBarDarkFont() {
        SofiaUtils.setStatusBarDarkFont(mActivity, true);
        return this;
    }

    @Override
    public Bar statusBarLightFont() {
        SofiaUtils.setStatusBarDarkFont(mActivity, false);
        return this;
    }

    @Override
    public Bar invasionStatusBar() {
        mStatusView.setVisibility(View.GONE);
        setContentViewFitSystemWindow(false);
        return this;
    }

    @Override
    public Bar statusBarBackground(int statusBarColor) {
        mStatusView.setBackgroundColor(statusBarColor);
        mStatusView.setVisibility(View.VISIBLE);
        setContentViewFitSystemWindow(true);
        return this;
    }

    @Override
    public Bar statusBarBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mStatusView.setBackground(drawable);
        } else {
            mStatusView.setBackgroundDrawable(drawable);
        }
        mStatusView.setVisibility(View.VISIBLE);
        setContentViewFitSystemWindow(true);
        return this;
    }

    @Override
    public Bar statusBarBackgroundAlpha(int alpha) {
        final Drawable background = mStatusView.getBackground();
        if (background != null) background.mutate().setAlpha(alpha);
        mStatusView.setVisibility(View.VISIBLE);
        setContentViewFitSystemWindow(true);
        return this;
    }
}