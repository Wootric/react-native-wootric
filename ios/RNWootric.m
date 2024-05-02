#import <WootricSDK/WootricSDK.h>
#import <WootricSDK/WTRSurveyDelegate.h>
#import "RNWootric.h"

@implementation RNWootric
{
  bool hasListeners;
}

- (NSArray<NSString *> *)supportedEvents {
  return @[
    @"surveyWillShow",
    @"surveyDidShow",
    @"surveyWillHide",
    @"surveyDidHide",
  ];
}

- (dispatch_queue_t)methodQueue
{
  return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(configureWithClientID:(NSString *)clientID accountToken:(NSString *)accountToken) {
  [Wootric configureWithClientID:clientID accountToken:accountToken];
  
  // https://docs.wootric.com/ios/#survey-delegate
  [Wootric setDelegate:self];
}

RCT_EXPORT_METHOD(setEndUserEmail:(NSString *)email) {
  [Wootric setEndUserEmail:email];
}

RCT_EXPORT_METHOD(setSurveyImmediately:(BOOL)flag) {
  [Wootric surveyImmediately:flag];
}

RCT_EXPORT_METHOD(setEndUserCreatedAt:(nonnull NSNumber *)externalCreatedAt) {
  [Wootric setEndUserCreatedAt:externalCreatedAt];
}

RCT_EXPORT_METHOD(setEndUserExternalId:(NSString *)externalId) {
  [Wootric setEndUserExternalId:externalId];
}

RCT_EXPORT_METHOD(setEndUserPhoneNumber:(NSString *)phoneNumber) {
  [Wootric setEndUserPhoneNumber:phoneNumber];
}

RCT_EXPORT_METHOD(setEndUserProperties:(NSDictionary *)customProperties) {
  [Wootric setEndUserProperties:customProperties];
}

RCT_EXPORT_METHOD(showOptOut:(BOOL)flag) {
  [Wootric showOptOut:flag];
}

RCT_EXPORT_METHOD(setEventName:(NSString *)eventName) {
  [Wootric setEventName:eventName];
}

RCT_EXPORT_METHOD(setFirstSurveyAfter:(nonnull NSNumber *)firstSurveyAfter) {
  [Wootric setFirstSurveyAfter:firstSurveyAfter];
}

RCT_EXPORT_METHOD(setCustomLanguage:(NSString *)languageCode) {
  [Wootric setCustomLanguage:languageCode];
}

RCT_EXPORT_METHOD(setCustomProductName:(NSString *)productName) {
  [Wootric setCustomProductName:productName];
}

RCT_EXPORT_METHOD(setCustomAudience:(NSString *)audience) {
  [Wootric setCustomAudience:audience];
}

RCT_EXPORT_METHOD(showDisclaimerText:(NSString *)disclaimerText link:(NSURL *)link linkText:(NSString *)linkText) {
  [Wootric showDisclaimerText:disclaimerText link:link linkText:linkText];
}

RCT_EXPORT_METHOD(forceSurvey:(BOOL)force) {
  [Wootric forceSurvey:force];
}

RCT_EXPORT_METHOD(showSurvey) {
  [Wootric showSurveyInViewController:[UIApplication sharedApplication].delegate.window.rootViewController];
}

// https://reactnative.dev/docs/native-modules-ios#sending-events-to-javascript
- (void)startObserving {
  hasListeners = YES;
}

- (void)stopObserving {
  hasListeners = NO;
}

- (void)willPresentSurvey {
  if (hasListeners) {
    [self sendEventWithName:@"surveyWillShow" body:nil ];
  }
}

- (void)didPresentSurvey {
  if (hasListeners) {
    [self sendEventWithName:@"surveyDidShow" body:nil ];
  }
}

- (void)willHideSurvey {
  if (hasListeners) {
    [self sendEventWithName:@"surveyWillHide" body:nil ];
  }
  
}

- (void)didHideSurvey:(NSDictionary *)data {
  if (hasListeners) {
    [self sendEventWithName:@"surveyDidHide" body:data ];
  }
  
}

@end
