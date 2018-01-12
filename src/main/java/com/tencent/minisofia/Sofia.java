
package com.tencent.minisofia;

import android.app.Activity;

public class Sofia {

    private Sofia() {
    }

    public static Bar with(Activity activity) {
        return new BarImpl(activity);
    }
}