export interface BatteryWhitelistModule {
	hasWhitelistIntent(): Promise<boolean>;
	startWhitelistActivity(): Promise<void>;
	isIgnoringBatteryOptimizations(): Promise<boolean>;
	startBatteryOptimizationSettings(): Promise<void>;
}

declare const BatteryWhitelist: BatteryWhitelistModule;

export default BatteryWhitelist;
