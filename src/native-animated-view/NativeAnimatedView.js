import React from 'react';
import ReactNative, { requireNativeComponent, UIManager } from 'react-native';

const NativeAnimatedView = requireNativeComponent("NativeAnimatedView");

class WrappedNativeAnimatedView extends React.Component {

    test() {
        this._invokeNativeUICommand('test')
    }

    _invokeNativeUICommand(command) {
        UIManager.dispatchViewManagerCommand(
            ReactNative.findNodeHandle(this),
            UIManager.NativeAnimatedView.Commands[command],
            [],
        );
    }

    render() {
        return <NativeAnimatedView {...this.props}/>
    }
}

module.exports = WrappedNativeAnimatedView;
