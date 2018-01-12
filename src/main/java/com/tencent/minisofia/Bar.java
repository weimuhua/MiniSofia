
package com.tencent.minisofia;

import android.graphics.drawable.Drawable;

public interface Bar {

    Bar statusBarDarkFont();

    Bar statusBarLightFont();

    Bar invasionStatusBar();

    Bar statusBarBackground(int statusBarColor);

    Bar statusBarBackground(Drawable drawable);

    Bar statusBarBackgroundAlpha(int alpha);
}