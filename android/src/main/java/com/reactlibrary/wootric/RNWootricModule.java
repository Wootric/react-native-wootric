package com.reactlibrary.wootric;

import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;

import android.app.Activity;
import android.util.Log;
import android.net.Uri;

import com.facebook.react.modules.core.DeviceEventManagerModule;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;

import com.wootric.androidsdk.Wootric;
import com.wootric.androidsdk.WootricSurveyCallback;

import java.util.HashMap;

public class RNWootricModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private Wootric wootric;

  public RNWootricModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  private static HashMap<String, String> toHashMap(ReadableMap readableMap) {
    HashMap<String, String> map = new HashMap<>();
    ReadableMapKeySetIterator iterator = readableMap.keySetIterator();

    while (iterator.hasNextKey()) {
      String key = iterator.nextKey();
      ReadableType type = readableMap.getType(key);

      switch (type) {
        case Null:
          map.put(key, null);
          break;
        case Boolean:
          map.put(key, Boolean.toString(readableMap.getBoolean(key)));
          break;
        case Number:
          map.put(key, Integer.toString(readableMap.getInt(key)));
          break;
        case String:
          map.put(key, readableMap.getString(key));
          break;
      }
    }

    return map;
  }

  @Override
  public String getName() {
    return "RNWootric";
  }

  @ReactMethod
  public void configureWithClientID(String clientId, String accountToken) {
    Activity currentActivity = getCurrentActivity();

    if (currentActivity == null) {
      Log.e("WOOTRIC", "Current activity is null");
      return;
    }

    try {
      wootric = Wootric.init(currentActivity, clientId, accountToken);
      wootric.setSurveyCallback(new RNWootricSurveyCallbacks());
    } catch (Exception e) {
      Log.e("WOOTRIC", e.toString());
    }
  }

  @ReactMethod
  public void setEndUserEmail(String email) {
    if (wootric == null) return;

    wootric.setEndUserEmail(email);
  }

  @ReactMethod
  public void setSurveyImmediately(boolean surveyImmediately) {
    if (wootric == null) return;

    wootric.setSurveyImmediately(surveyImmediately);
  }

  @ReactMethod
  public void setEndUserCreatedAt(double createdAt) {
    if (wootric == null) return;

    wootric.setEndUserCreatedAt((long) createdAt);
  }

  @ReactMethod
  public void setEndUserExternalId(String externalId) {
    if (wootric == null) return;

    wootric.setEndUserExternalId(externalId);
  }

  @ReactMethod
  public void setEndUserPhoneNumber(String phoneNumber) {
    if (wootric == null) return;

    wootric.setEndUserPhoneNumber(phoneNumber);
  }

  @ReactMethod
  public void setEndUserProperties(ReadableMap properties) {
    if (wootric == null) return;

    wootric.setProperties(toHashMap(properties));
  }

  @ReactMethod
  public void showOptOut(boolean flag) {
    if (wootric == null) return;

    wootric.setShowOptOut(flag);
  }

  @ReactMethod
  public void setFirstSurveyAfter(int value) {
    if (wootric == null) return;

    wootric.setFirstSurveyDelay(value);
  }

  @ReactMethod
  public void setCustomLanguage(String language) {
    if (wootric == null) return;

    wootric.setLanguageCode(language);
  }

  @ReactMethod
  public void setCustomProductName(String productName) {
    if (wootric == null) return;

    wootric.setProductName(productName);
  }

  @ReactMethod
  public void setCustomAudience(String audience) {
    if (wootric == null) return;

    wootric.setRecommendTarget(audience);
  }

  @ReactMethod
  public void setEventName(String eventName) {
    if (wootric == null) return;

    wootric.setEventName(eventName);
  }

  @ReactMethod
  public void showDisclaimerText(String disclaimerText, String disclaimerLinkURL, String disclaimerLinkText) {
    if (wootric == null) return;

    Uri disclaimerLinkURI = Uri.parse(disclaimerLinkURL);
    wootric.showDisclaimer(disclaimerText, disclaimerLinkURI, disclaimerLinkText);
  }

  @ReactMethod
  public void forceSurvey(boolean force) {
    // Workaround for Android
    this.setSurveyImmediately(force);
  }

  @ReactMethod
  public void showSurvey() {
    if (wootric == null) return;

    try {
      runOnUiThread(new Thread(new Runnable() {
        public void run() {
          wootric.survey();
        }
      }));
    } catch (Exception e) {
      Log.e("WOOTRIC", e.toString());
    }
  }

  // https://docs.wootric.com/android/#survey-callbacks
  private class RNWootricSurveyCallbacks implements WootricSurveyCallback {
    // https://reactnative.dev/docs/native-modules-android?android-language=java#sending-events-to-javascript
    private void sendEvent(String eventName, WritableMap params) {
      reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
    }

    @Override
    public void onSurveyWillShow() {
      sendEvent("surveyWillShow", null);
    }

    @Override
    public void onSurveyDidShow() {
      sendEvent("surveyDidShow", null);
    }

    @Override
    public void onSurveyWillHide() {
      sendEvent("surveyWillHide", null);
    }

    @Override
    public void onSurveyDidHide(HashMap values) {
      sendEvent("surveyDidHide", Arguments.makeNativeMap(values));
    }
  }
}
