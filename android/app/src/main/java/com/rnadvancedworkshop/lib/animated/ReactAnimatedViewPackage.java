package com.rnadvancedworkshop.lib.animated;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.rnadvancedworkshop.lib.animated.view.RNAnimatedViewManager;

import java.util.Collections;
import java.util.List;

/**
 * Native package for the library.
 */
public class ReactAnimatedViewPackage implements ReactPackage {

    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @NonNull
    @Override
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
        return Collections.singletonList(new RNAnimatedViewManager(new TimeInterpolationFactory()));
    }
}
