/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React from "react";
import {
  SafeAreaView,
  StyleSheet,
  View,
} from "react-native";
import SimpleCircleView from "./src/SimpleCircleView";
const NativeAnimatedView = require("./lib/native-animated-view/NativeAnimatedView");

const VIEW_SIZE = 50;
const ANIM_DURATION = 3000;

const App: () => React$Node = () => {
  const [parentViewHeight, setParentViewHeight] = React.useState(0);
  const ref = React.useRef(null);

  React.useEffect(() => {
    ref.current.start();
  }, [ref, parentViewHeight]);

  return (
    <View>
      <SafeAreaView>
        <View
          style={styles.container}
          onLayout={event => {
            setParentViewHeight(event.nativeEvent.layout.height);
          }}
        >
          <View>
            <NativeAnimatedView
              y={parentViewHeight - VIEW_SIZE}
              ref={ref}
              interpolation={{ type: "bounce" }}
              duration={ANIM_DURATION}
            >
              <SimpleCircleView size={VIEW_SIZE} />
            </NativeAnimatedView>
          </View>
        </View>
      </SafeAreaView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    width: "100%",
    height: "100%",
    flexDirection: "column",
    alignItems: "center"
  }
});

export default App;
