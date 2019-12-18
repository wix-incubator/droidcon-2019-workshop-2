package com.rnadvancedworkshop.lib.animated.view;

import android.animation.TimeInterpolator;

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

/**
 * Native view animation manager.
 */
public class RNAnimatedViewManager extends ViewGroupManager<RNAnimatedView> {

    private static final String REACT_CLASS = "NativeAnimatedView";

    private static final String COMMAND_START_ANIMATION = "start";
    private static final int COMMAND_START_ANIMATION_TYPE = 1;

    private static final String COMMAND_STOP_ANIMATION = "stop";
    private static final int COMMAND_STOP_ANIMATION_TYPE = 2;

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
        return MapBuilder.of(COMMAND_START_ANIMATION, COMMAND_START_ANIMATION_TYPE,
                COMMAND_STOP_ANIMATION, COMMAND_STOP_ANIMATION_TYPE);
    }

    @Override
    public void receiveCommand(@NonNull RNAnimatedView root, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case COMMAND_START_ANIMATION_TYPE: {
                root.playAnimation();
                break;
            }
            case COMMAND_STOP_ANIMATION_TYPE: {
                root.pauseAnimation();
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown commandId: \"" + commandId + "\"");
            }
        }
    }

}
