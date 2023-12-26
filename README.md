<p align="center">
  <a href="https://circleci.com/gh/Wootric/react-native-wootric"><img src="https://circleci.com/gh/Wootric/react-native-wootric.svg?style=svg" alt="circle-ci: build"></a>
  <a href="https://choosealicense.com/licenses/mit/"><img src="https://img.shields.io/badge/License-MIT-blue.svg" alt="License: MIT"></a>
</p>

# react-native-wootric

## Getting started

`$ npm install @wootric/react-native-wootric --save`

### iOS

Install the `WootricSDK` using Cocoapods

```bash
$ cd ios
$ pod install
```

### ReactNative <= 0.60

As `@wootric/react-native-wootric` contains native codes so requires your project to link our native module library:

`$ react-native link @wootric/react-native-wootric` //Do not run if you are using React Native version 0.60 or greater.

> [`Autolinking`](https://github.com/react-native-community/cli/blob/master/docs/autolinking.md) is a replacement for `react-native link`. If you have been using React Native before version 0.60, please `unlink` native dependencies if you have any from a previous install.

### Manual installation
[Linking Libraries](https://facebook.github.io/react-native/docs/linking-libraries-ios)

#### Windows
Support for Windows is currently not available.

## Usage
```javascript
import RNWootric from '@wootric/react-native-wootric';

RNWootric.configureWithClientID("client_id", "account_token"); //replace `client_id` and `account_token` with yours from Wootric dashboard account settings.
RNWootric.setEndUserEmail("react_example@wootric.com");
RNWootric.setSurveyImmediately(true);
RNWootric.setEndUserCreatedAt(1234567890);
RNWootric.setEndUserExternalId("external_id_1234");
RNWootric.setEndUserPhoneNumber("+17865551234");
RNWootric.setEndUserProperties({first_name: "React", last_name: "Native"});
RNWootric.showOptOut(true);
RNWootric.setFirstSurveyAfter(5);
RNWootric.setCustomLanguage("ES");
RNWootric.setCustomProductName("Wootric React Native");
RNWootric.setCustomAudience("un amigo");
RNWootric.showDisclaimerText("Learn how we handle your feedback","https://example.com/terms-of-use","here");
RNWootric.showSurvey();
```

## Possbile Errors and Solutions
1. If link fails then please delete node_modules dir and package-lock.json from your react native project and run `npm install` again.

2. If you get `Error:Execution failed for task ':app:processDebugResources'. > java.io.IOException: Could not delete folder “”` while running your react native for android (ex. react-native run-android) then please clean your android project (ex. gradlew clean) and run project again.

## License
[MIT License](https://choosealicense.com/licenses/mit/)
