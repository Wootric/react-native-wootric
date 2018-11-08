#import <WootricSDK/WootricSDK.h>
#import "RNWootric.h"

@implementation RNWootric

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE(WootricSDK);

RCT_EXPORT_METHOD(showSurvey) {
  [Wootric showSurveyInViewController:[UIApplication sharedApplication].delegate.window.rootViewController];
}

@end
  
