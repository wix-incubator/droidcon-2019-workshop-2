package com.rnadvancedworkshop.lib.animated;

import android.animation.TimeInterpolator;
import android.view.animation.BounceInterpolator;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableMap;

import javax.annotation.Nullable;

public class TimeInterpolationFactory implements ITimeInterpolationFactory {

    private static final String BOUNCE_INTERPOLATOR_TYPE = "bounce";

    @Override public @Nullable TimeInterpolator createInterpolator(@NonNull ReadableMap params) {
        String type = params.getString("type");
        if (type != null ){
            if (BOUNCE_INTERPOLATOR_TYPE.equals(type)) {
                return new BounceInterpolator();
            }
        }
        return null;
    }

}
