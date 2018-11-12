# react-native-wootric

## Getting started

`$ npm install react-native-wootric --save`

### Mostly automatic installation

`$ react-native link react-native-wootric`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-wootric` and add `RNWootric.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNWootric.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

##### iOS only: additional steps for iOS

- Download the SDK and add it to your project without using any dependency manager.

1. [Download](https://github.com/Wootric/WootricSDK-iOS/releases) & unzip the Wootric SDK

2. In your Xcode project, drag & drop the WootricSDK.framework in the RNWootric.xcodeproj 

Your project will look something like this

![Xcode](https://user-images.githubusercontent.com/1431421/48356656-bb66e180-e664-11e8-98a5-d931a17be807.png)

*Make sure the "Copy items if needed" checkbox is checked.*

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNWootricPackage;` to the imports at the top of the file
  - Add `new RNWootricPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-wootric'
  	project(':react-native-wootric').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-wootric/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-wootric')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNWootric.sln` in `node_modules/react-native-wootric/windows/RNWootric.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Wootric.RNWootric;` to the usings at the top of the file
  - Add `new RNWootricPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNWootric from 'react-native-wootric';

RNWootric.configureWithClientID("client_id", "NPS-1234asdf");
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
RNWootric.showSurvey();
```
