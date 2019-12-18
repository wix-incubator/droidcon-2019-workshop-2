/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';
import {init} from "./init";

init();
AppRegistry.registerComponent(appName, () => App);
