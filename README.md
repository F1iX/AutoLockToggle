# Android AutoLockToggle
Adds an Android quick settings tile to toggle automatic screen lock timer between 0 seconds and 100 minutes.

Immediate screen lock upon power button press usually comes in handy to prevent your phone from going crazy in your pocket. However, when mounted to a bicycle or car phone mount, you might want to turn off your screen and re-enable it without having to unlock.

# Install
1. Clone this repository and build and install with Android Studio
1. Connect your device to a computer with Android Debugging Bridge installed and run
    ```
    adb shell pm grant me.keppler.autolocktoggle android.permission.WRITE_SECURE_SETTINGS
    ```
1. In Android's screen lock settings, disable *Power button instantly locks*
1. Add quick settings tile to quick settings drawer
1. Upon first usage, allow AutoLockToggle to modify system settings