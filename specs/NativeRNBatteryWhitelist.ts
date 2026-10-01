import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
	hasWhitelistIntent(): Promise<boolean>;
	startWhitelistActivity(): Promise<void>;
	isIgnoringBatteryOptimizations(): Promise<boolean>;
	startBatteryOptimizationSettings(): Promise<void>;
}

export default TurboModuleRegistry.get<Spec>('RNBatteryWhitelist');
