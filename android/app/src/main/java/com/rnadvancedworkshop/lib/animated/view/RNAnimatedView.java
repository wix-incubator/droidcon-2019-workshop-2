package com.rnadvancedworkshop.lib.animated.view;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.arch.core.util.Function;

import com.rnadvancedworkshop.lib.animated.Utils;

import java.util.HashMap;
import java.util.Map;

public class RNAnimatedView extends ViewGroup {

    private static final String MODIFIER_DURATION = "duration";
    private static final String MODIFIER_Y = "y";
    private static final String MODIFIER_INTERPOLATION = "interpolation";

    private Map<String, Function<ViewPropertyAnimator, ViewPropertyAnimator>> animationModifiers = new HashMap<>();

    public RNAnimatedView(Context context) {
        super(context);
    }

    public RNAnimatedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RNAnimatedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RNAnimatedView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
    }

    void setDuration(long durationMillis) {
        animationModifiers.put(MODIFIER_DURATION, animator -> animator.setDuration(durationMillis));
    }

    void setTargetY(float y) {
        if (Float.isNaN(y)) {
            animationModifiers.remove(MODIFIER_Y);
        } else {
            animationModifiers.put(MODIFIER_Y, animator -> animator.y(Utils.dpToPx(getContext(), y)));
        }
    }

    void setInterpolation(@Nullable TimeInterpolator interpolation) {
        if (interpolation == null) {
            animationModifiers.remove(MODIFIER_INTERPOLATION);
        } else {
            animationModifiers.put(MODIFIER_INTERPOLATION, animator -> animator.setInterpolator(interpolation));
        }
    }
}
