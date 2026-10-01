# react-native-battery-whitelist

Android-only React Native Turbo Native Module that checks whether the current device exposes a manufacturer-specific battery optimization / auto-start whitelist screen and can open the first available screen.

## Requirements

- React Native 0.81 or newer
- New Architecture enabled
- Android

This package no longer supports the Legacy Architecture.

## Installation

```bash
yarn add react-native-battery-whitelist
```

or:

```bash
npm install react-native-battery-whitelist
```

React Native autolinking handles the Android package automatically.

## Usage

```javascript
import BatteryWhitelist from 'react-native-battery-whitelist';

async function tryStartWhitelist() {
    try {
        const hasIntent = await BatteryWhitelist.hasWhitelistIntent();
        if (hasIntent) await BatteryWhitelist.startWhitelistActivity();
    } catch (error) {
        console.error(error);
    }
}
```

### API

#### `hasWhitelistIntent(): Promise<boolean>`

Returns `true` when at least one known manufacturer-specific whitelist activity is available on the device.

#### `startWhitelistActivity(): Promise<void>`

Opens the first available known whitelist activity. Rejects when no supported activity is available or the Android activity cannot be started.

## Credits

Based on https://stackoverflow.com/a/51726040/8116839

## License

MIT
