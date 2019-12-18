package com.rnadvancedworkshop.lib.animated;

import android.animation.TimeInterpolator;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableMap;

import javax.annotation.Nullable;

/**
 * Factory that creates instances of {@link TimeInterpolator} based on their type and parameters.
 */
public interface ITimeInterpolationFactory {

    /**
     * Creates new {@link TimeInterpolator} from given parameters.
     * @param params to create {@link TimeInterpolator} from.
     * @return an implementation of {@link TimeInterpolator}, or null if invalid.
     */
    @Nullable TimeInterpolator createInterpolator(@NonNull ReadableMap params);

}
