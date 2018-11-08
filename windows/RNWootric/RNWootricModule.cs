using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Wootric.RNWootric
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNWootricModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNWootricModule"/>.
        /// </summary>
        internal RNWootricModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNWootric";
            }
        }
    }
}
