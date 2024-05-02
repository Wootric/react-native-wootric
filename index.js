
import { NativeModules, NativeEventEmitter } from 'react-native';

const { RNWootric } = NativeModules;

RNWootric.eventEmitter = new NativeEventEmitter(RNWootric)

export default RNWootric;
