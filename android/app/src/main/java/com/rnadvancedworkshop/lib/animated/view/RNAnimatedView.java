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

/**
 * Implementation of the view animation in native side.
 */
public class RNAnimatedView extends ViewGroup {

    private static final String MODIFIER_DURATION = "duration";
    private static final String MODIFIER_Y = "y";
    private static final String MODIFIER_INTERPOLATION = "interpolation";

    /**
     * Current active animation.
     */
    private @Nullable ViewPropertyAnimator animator;

    /**
     * Mapping of modifier type to function that execute the modification.
     */
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

    /**
     * Configures the animation with a duration
     * @param durationMillis
     */
    void setDuration(long durationMillis) {
        animationModifiers.put(MODIFIER_DURATION, animator -> animator.setDuration(durationMillis));
    }

    /**
     * Configures the animation with a target Y coordinates.
     * @param y pixels.
     */
    void setTargetY(float y) {
        if (Float.isNaN(y)) {
            animationModifiers.remove(MODIFIER_Y);
        } else {
            animationModifiers.put(MODIFIER_Y, animator -> animator.y(Utils.dpToPx(getContext(), y)));
        }
    }

    /**
     * Configures the animation with a {@link TimeInterpolator}.
     * @param interpolation to configure with.
     */
    void setInterpolation(@Nullable TimeInterpolator interpolation) {
        if (interpolation == null) {
            animationModifiers.remove(MODIFIER_INTERPOLATION);
        } else {
            animationModifiers.put(MODIFIER_INTERPOLATION, animator -> animator.setInterpolator(interpolation));
        }
    }

    /**
     * Plays the animation with all its properties.
     */
    void playAnimation() {
        View firstChild = getChildAt(0);
        if (firstChild != null) {
            animator = firstChild.animate();
            for (Function<ViewPropertyAnimator, ViewPropertyAnimator> modifier: animationModifiers.values()) {
                modifier.apply(animator);
            }
            if (animator != null) {
                animator.start();
            }
        }
    }

    /**
     * Pauses current animation.
     */
    void pauseAnimation() {
        if (animator != null) {
            animator.cancel();
        }
    }
}
