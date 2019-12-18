import {StyleSheet, View} from 'react-native';
import React from 'react';

const SimpleCircleView = (props) => {
  return (
    <View
      style={styles.simpleView}
      width={props.size}
      height={props.size}
      borderRadius={props.size / 2}
    />
  );
};

const styles = StyleSheet.create({
  simpleView: {
    backgroundColor: '#2e72ff',
  },
});

export default SimpleCircleView;
