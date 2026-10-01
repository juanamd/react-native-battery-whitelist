# react-native-battery-whitelist

React Native TurboModule for opening manufacturer-specific Android battery and background-startup settings.

This package supports Android only and uses the React Native New Architecture.

## Requirements

- React Native 0.81 or newer
- Android
- New Architecture enabled

For Legacy Architecture support use version 2.0 or older.

## Installation

```bash
npm install react-native-battery-whitelist
```

or

```bash
yarn add react-native-battery-whitelist
```

No manual native registration is required when using React Native autolinking.

## Usage

```ts
import BatteryWhitelist from 'react-native-battery-whitelist';

const whitelistAvailable = await BatteryWhitelist.hasWhitelistIntent();

if (whitelistAvailable) {
	await BatteryWhitelist.startWhitelistActivity();
}

const batteryOptimizationIgnored = await BatteryWhitelist.isIgnoringBatteryOptimizations();

if (!batteryOptimizationIgnored) {
	await BatteryWhitelist.startBatteryOptimizationSettings();
}
```

`hasWhitelistIntent()` checks whether any supported manufacturer-specific settings activity is available. The generic Android battery-optimization settings screen is not included in this result.

`startWhitelistActivity()` opens the first supported manufacturer-specific settings activity. It rejects if no supported activity can be opened.

`isIgnoringBatteryOptimizations()` checks whether the app is currently on Android's system battery-optimization allowlist. On Android versions before 6.0 (API 23), where this allowlist does not exist, it resolves to `true`.

`startBatteryOptimizationSettings()` opens Android's generic battery-optimization allowlist screen on Android 6.0 (API 23) and newer. It rejects on older Android versions or when the settings activity is unavailable.

## Supported settings

The package includes known settings activities for Samsung, Huawei/Honor, Xiaomi, OnePlus, OPPO/ColorOS, Realme, Vivo/iQOO, ASUS, Tecno/Infinix/itel (Transsion), HTC, Nokia, LeTV, and Meizu.

OEM settings are private implementation details and can change between Android/OEM versions. The package therefore checks every candidate at runtime and falls back to another known candidate when possible.

## TypeScript

TypeScript definitions are included in the package.

## Notes

Android 11 and newer restrict visibility of other installed packages. The library declares the OEM packages and implicit settings actions it explicitly queries in its manifest using `<queries>`.

This library does not request the Android `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS` permission. The generic method opens the system battery-optimization allowlist screen rather than requesting the exemption directly. OEM-specific settings screens and behavior depend on the device and software version.
