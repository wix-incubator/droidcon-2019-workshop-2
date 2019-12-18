import React from 'react';
import ReactNative, { requireNativeComponent, UIManager } from 'react-native';

const NativeAnimatedView = requireNativeComponent("NativeAnimatedView");

class WrappedNativeAnimatedView extends React.Component {
    start() {
        this._invokeNativeUICommand('start');
    }

    stop() {
        this._invokeNativeUICommand('stop');
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
