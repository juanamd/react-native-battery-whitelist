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

const available = await BatteryWhitelist.hasWhitelistIntent();

if (available) {
	await BatteryWhitelist.startWhitelistActivity();
}
```

`hasWhitelistIntent()` checks whether any supported manufacturer-specific settings activity is available. On Android 6.0 (API 23) and newer, the standard Android battery-optimization settings screen is also used as a fallback.

`startWhitelistActivity()` opens the first supported settings activity. It rejects if no supported activity can be opened.

## Supported settings

The package includes known settings activities for Samsung, Huawei/Honor, Xiaomi, OnePlus, OPPO/ColorOS, Realme, Vivo/iQOO, ASUS, Tecno/Infinix/itel (Transsion), HTC, Nokia, LeTV, and Meizu.

OEM settings are private implementation details and can change between Android/OEM versions. The package therefore checks every candidate at runtime and falls back to another known candidate when possible.

## TypeScript

TypeScript definitions are included in the package.

## Notes

Android 11 and newer restrict visibility of other installed packages. The library declares the OEM packages it explicitly queries in its manifest using `<queries>`.

This library does not request the Android `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS` permission. The standard fallback opens the system battery-optimization allowlist screen rather than requesting the exemption directly. OEM-specific settings screens and behavior depend on the device and software version.
