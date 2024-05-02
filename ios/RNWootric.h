
#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif
#import <React/RCTEventEmitter.h>
#import "WootricSDK/WTRSurveyDelegate.h"


@interface RNWootric : RCTEventEmitter <RCTBridgeModule, WTRSurveyDelegate>

@end
  