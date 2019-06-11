package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.wootric.androidsdk.Wootric;

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
        map.put(key, Double.toString(readableMap.getDouble(key)));
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
    wootric = Wootric.init(getCurrentActivity(), clientId, accountToken);
  }

  @ReactMethod
  public void setEndUserEmail(String email) {
    wootric.setEndUserEmail(email);
  }

  @ReactMethod
  public void setSurveyImmediately(boolean surveyImmediately) {
    wootric.setSurveyImmediately(surveyImmediately);
  }

  @ReactMethod
  public void setEndUserCreatedAt(double createdAt) {
    wootric.setEndUserCreatedAt((long) createdAt);
  }

  @ReactMethod
  public void setEndUserExternalId(String externalId) {
    wootric.setEndUserExternalId(externalId);
  }

  @ReactMethod
  public void setEndUserPhoneNumber(String phoneNumber) {
    wootric.setEndUserPhoneNumber(phoneNumber);
  }

  @ReactMethod
  public void setEndUserProperties(ReadableMap properties) {
    wootric.setProperties(toHashMap(properties));
  }

  @ReactMethod
  public void showOptOut(boolean flag) {
    wootric.setShowOptOut(flag);
  }

  @ReactMethod
  public void setFirstSurveyAfter(int value) {
    wootric.setFirstSurveyDelay(value);
  }

  @ReactMethod
  public void setCustomLanguage(String language) {
    wootric.setLanguageCode(language);
  }

  @ReactMethod
  public void setCustomProductName(String productName) {
    wootric.setProductName(productName);
  }

  @ReactMethod
  public void setCustomAudience(String audience) {
    wootric.setRecommendTarget(audience);
  }

  @ReactMethod
  public void forceSurvey(boolean force) {
    // Workaround for Android
    this.setSurveyImmediately(force);
  }

  @ReactMethod
  public void showSurvey() {
    wootric.survey();
  }
}
