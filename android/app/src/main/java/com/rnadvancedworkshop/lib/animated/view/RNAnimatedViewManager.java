package com.rnadvancedworkshop.lib.animated.view;

import android.animation.TimeInterpolator;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.rnadvancedworkshop.lib.animated.ITimeInterpolationFactory;

import java.util.Map;

public class RNAnimatedViewManager extends ViewGroupManager<RNAnimatedView> {

    private static final String LOG_TAG = "RNAnimatedViewManager";

    private static final String REACT_CLASS = "NativeAnimatedView";

    private static final String COMMAND_TEST_ANIMATION = "test";
    private static final int COMMAND_TEST_ANIMATION_TYPE = 1;

    private final ITimeInterpolationFactory interpolationFactory;

    public RNAnimatedViewManager(ITimeInterpolationFactory interpolationFactory) {
        this.interpolationFactory = interpolationFactory;
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected RNAnimatedView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new RNAnimatedView(reactContext);
    }

    @ReactProp(name = "duration", defaultFloat = 400f)
    public void setAnimationDuration(RNAnimatedView view, float durationMS) {
        view.setDuration((long) durationMS);
    }

    @ReactProp(name = "y", defaultFloat = Float.NaN)
    public void setAnimationTargetY(RNAnimatedView view, float y) {
        view.setTargetY(y);
    }

    @ReactProp(name = "interpolation")
    public void setInterpolation(RNAnimatedView view, @Nullable ReadableMap map) {
        @Nullable TimeInterpolator interpolator = null;
        if (map != null) {
            interpolator = interpolationFactory.createInterpolator(map);
        }
        view.setInterpolation(interpolator);
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(COMMAND_TEST_ANIMATION, COMMAND_TEST_ANIMATION_TYPE);
    }

    @Override
    public void receiveCommand(@NonNull RNAnimatedView root, int commandId, @Nullable ReadableArray args) {
        if (commandId == COMMAND_TEST_ANIMATION_TYPE) {
            Log.d(LOG_TAG, "Received command from JS!");
        } else {
            throw new IllegalArgumentException("Unknown commandId: \"" + commandId + "\"");
        }
    }

}
