package com.rnadvancedworkshop.lib.animated;

import android.content.Context;
import android.util.TypedValue;

public class Utils {

    public static float dpToPx(Context context, float pixels) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                pixels,
                context.getResources().getDisplayMetrics());
    }

}
