export interface BatteryWhitelistModule {
	hasWhitelistIntent(): Promise<boolean>;
	startWhitelistActivity(): Promise<void>;
}

declare const BatteryWhitelist: BatteryWhitelistModule;

export default BatteryWhitelist;
