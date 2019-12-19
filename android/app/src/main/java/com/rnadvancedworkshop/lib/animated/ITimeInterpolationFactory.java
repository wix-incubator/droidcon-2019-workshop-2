package com.rnadvancedworkshop.lib.animated;

import android.animation.TimeInterpolator;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableMap;

import javax.annotation.Nullable;

public interface ITimeInterpolationFactory {

    @Nullable TimeInterpolator createInterpolator(@NonNull ReadableMap params);

}
