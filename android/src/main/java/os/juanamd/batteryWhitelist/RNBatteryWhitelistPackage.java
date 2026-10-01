package os.juanamd.batteryWhitelist;

import com.facebook.react.BaseReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;

import java.util.HashMap;
import java.util.Map;

public class RNBatteryWhitelistPackage extends BaseReactPackage {
	@Override
	public NativeModule getModule(String name, ReactApplicationContext reactContext) {
		if (name.equals(RNBatteryWhitelistModule.NAME)) {
			return new RNBatteryWhitelistModule(reactContext);
		}
		return null;
	}

	@Override
	public ReactModuleInfoProvider getReactModuleInfoProvider() {
		return new ReactModuleInfoProvider() {
			@Override
			public Map<String, ReactModuleInfo> getReactModuleInfos() {
				Map<String, ReactModuleInfo> map = new HashMap<>();
				map.put(RNBatteryWhitelistModule.NAME, new ReactModuleInfo(
					RNBatteryWhitelistModule.NAME,
					RNBatteryWhitelistModule.NAME,
					false,
					false,
					false,
					true
				));
				return map;
			}
		};
	}
}
