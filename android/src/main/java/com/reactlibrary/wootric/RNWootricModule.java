package com.reactlibrary.wootric;

import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;

import android.app.Activity;
import android.util.Log;
import android.net.Uri;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.wootric.androidsdk.Wootric;
import com.wootric.androidsdk.Wootric;
import com.wootric.androidsdk.objects.WootricCustomThankYou;

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
  public void forceSurvey(boolean force) {
    // Workaround for Android
    this.setSurveyImmediately(force);
  }

  @ReactMethod
  public void showSurvey() {
    if (wootric == null) return;

    try {
      runOnUiThread (new Thread(new Runnable() {
        public void run() {
          wootric.survey();
        }
      }));
    } catch (Exception e) {
      Log.e("WOOTRIC", e.toString());
    }
  }

  @ReactMethod
  public void setPromoterThankYouLinkWithText(String promoterLinkText, String promoterLinkUri ) {

    WootricCustomThankYou customThankYou = new WootricCustomThankYou();

    customThankYou.setPromoterLinkUri(Uri.parse(promoterLinkUri));
    customThankYou.setPromoterLinkText(promoterLinkText);

    if (wootric == null) return;
    wootric.setCustomThankYou(customThankYou);
  }

  @ReactMethod
  public void setPassiveThankYouLinkWithText(String passiveLinkText, String passiveLinkUri ) {

    WootricCustomThankYou customThankYou = new WootricCustomThankYou();

    customThankYou.setPassiveLinkUri(Uri.parse(passiveLinkUri));
    customThankYou.setPassiveLinkText(passiveLinkText);
    
    if (wootric == null) return;
    wootric.setCustomThankYou(customThankYou);
  }
}
