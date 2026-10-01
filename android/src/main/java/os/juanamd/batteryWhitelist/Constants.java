package os.juanamd.batterywhitelist;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import java.util.Arrays;
import java.util.List;

public class Constants {
	public static List<Intent> getPowerManagerIntents() {
		return Arrays.asList(
			// Samsung
			getIntent("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity"),
			getIntent("com.samsung.android.sm", "com.samsung.android.sm.ui.battery.BatteryActivity"),
			// Samsung (newer One UI)
			getIntent("com.samsung.android.lool", "com.samsung.android.sm.battery.ui.usage.CheckableAppListActivity"),
			getIntent("com.samsung.android.lool", "com.samsung.android.sm.battery.ui.BatteryActivity"),
			// Huawei
			getIntent("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"),
			getIntent("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"),
			getIntent("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity"),
			// Xiaomi
			getIntent("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"),
			// One plus
			getIntent("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity"),
			getActionIntent("com.android.settings.action.BACKGROUND_OPTIMIZE"),
			// Oppo / ColorOS
			getIntent("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"),
			getIntent("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity"),
			getIntent("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity"),
			getIntent("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerUsageModelActivity"),
			getIntent("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerSaverModeActivity"),
			getIntent("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerConsumptionActivity"),
			// Oppo / ColorOS (newer OPlus package names)
			getIntent("com.oplus.safe", "com.oplus.safe.permission.startup.StartupAppListActivity"),
			getIntent("com.oplus.safecenter", "com.oplus.safecenter.startupapp.view.StartupAppListActivity"),
			// Realme
			getIntent("com.realme.security", "com.realme.security.permission.startup.StartupAppListActivity"),
			// Vivo
			getIntent("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"),
			getIntent("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"),
			getIntent("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager"),
			// Asus
			getIntent("com.asus.mobilemanager", "com.asus.mobilemanager.powersaver.PowerSaverSettings"),
			getIntent("com.asus.mobilemanager", "com.asus.mobilemanager.autostart.AutoStartActivity"),
			getIntent("com.asus.mobilemanager", "com.asus.mobilemanager.entry.FunctionActivity")
				.setData(Uri.parse("mobilemanager://function/entry/AutoStart")),
			// Transsion (Tecno / Infinix / itel)
			getIntent("com.transsion.phonemaster", "com.cyin.himgr.autostart.AutoStartActivity"),
			getIntent("com.transsion.phonemaster", "com.transsion.phonemaster.startupmanager.StartupManagerActivity"),
			// Htc
			getIntent("com.htc.pitroad", "com.htc.pitroad.landingpage.activity.LandingPageActivity"),
			// Nokia
			getIntent("com.evenwell.powersaving.g3", "com.evenwell.powersaving.g3.exception.PowerSaverExceptionActivity"),
			// Letv
			getIntent("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")
				.setData(Uri.parse("mobilemanager://function/entry/AutoStart")),
			// Meizu
			getIntent("com.meizu.safe", "com.meizu.safe.security.SHOW_APPSEC")
				.addCategory(Intent.CATEGORY_DEFAULT)
		);
	}

	public static Intent getGenericBatteryOptimizationIntent() {
		return new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
	}

	private static Intent getIntent(String packageName, String className) {
		return new Intent().setComponent(new ComponentName(packageName, className));
	}

	private static Intent getActionIntent(String action) {
		return new Intent(action);
	}
}
