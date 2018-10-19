# react-native-battery-whitelist
Checks if any manufacturer-specific opt-in battery optimization whitelist is available for the current Android device. Shows battery saving whitelist screen if this is the case.

## Usage
```javascript
import RNBatteryWhitelist from "react-native-battery-whitelist";

RNBatteryWhitelist.hasWhitelistIntent((hasIntent) => {
    if (hasIntent) RNBatteryWhitelist.startWhitelistActivity();
});
```

## Credits
Based on https://stackoverflow.com/a/51726040/8116839
