# react-native-battery-whitelist
Checks if any manufacturer-specific opt-in battery optimization whitelist is available for the current Android device. Shows battery saving whitelist screen if this is the case.

## Usage
```javascript
import BatteryWhitelist from "react-native-battery-whitelist";

async function tryStartWhitelist() {
    try {
        const hasIntent = await BatteryWhitelist.hasWhitelistIntent();
        if (hasIntent) await BatteryWhitelist.startWhitelistActivity();
    } catch (error) {
        console.error(error);
    }
}
```

## Credits
Based on https://stackoverflow.com/a/51726040/8116839
