/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React from 'react';
import {SafeAreaView, StyleSheet, View, Animated, Easing} from 'react-native';
import SimpleCircleView from './src/SimpleCircleView';

const VIEW_SIZE = 50;
const ANIM_DURATION = 6000;

const App: () => React$Node = () => {
  const [parentViewHeight, setParentViewHeight] = React.useState(0);
  const [relativePosition] = React.useState(new Animated.Value(0));

  React.useEffect(() => {
    Animated.timing(relativePosition, {
      toValue: parentViewHeight - VIEW_SIZE,
      duration: ANIM_DURATION,
      easing: Easing.bounce,
    }).start();
  }, [relativePosition, parentViewHeight]);

  return (
    <View>
      <SafeAreaView>
        <View
          style={styles.container}
          onLayout={event => {
            setParentViewHeight(event.nativeEvent.layout.height);
          }}>
          <Animated.View style={{top: relativePosition}}>
            <SimpleCircleView size={VIEW_SIZE} />
          </Animated.View>
        </View>
      </SafeAreaView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    width: '100%',
    height: '100%',
    flexDirection: 'column',
    alignItems: 'center',
  },
});

export default App;
