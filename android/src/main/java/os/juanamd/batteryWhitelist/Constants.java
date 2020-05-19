package os.juanamd.batteryWhitelist;

import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import android.net.Uri;
import java.util.Arrays;
import java.util.List;

public class Constants {
	public static List<Intent> POWERMANAGER_INTENTS = Arrays.asList(
			// Samsung
			getIntent("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity"),
			getIntent("com.samsung.android.sm", "com.samsung.android.sm.ui.battery.BatteryActivity"),
			// Huawei
			getIntent("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"),
			getIntent("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"),
			getIntent("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity"),
			// Xiaomi
			getIntent("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"),
			// One plus
			getIntent("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity"),
			// Oppo
			getIntent("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"),
			getIntent("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity"),
			getIntent("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")
				.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS),
			getIntent("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerUsageModelActivity"),
			getIntent("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerSaverModeActivity"),
			getIntent("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerConsumptionActivity"),
			// Vivo
			getIntent("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"),
			getIntent("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"),
			getIntent("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager"),
			// Asus
			getIntent("com.asus.mobilemanager", "com.asus.mobilemanager.powersaver.PowerSaverSettings"),
			getIntent("com.asus.mobilemanager", "com.asus.mobilemanager.autostart.AutoStartActivity"),
			getIntent("com.asus.mobilemanager", "com.asus.mobilemanager.entry.FunctionActivity")
				.setData(Uri.parse("mobilemanager://function/entry/AutoStart")),
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

	private static Intent getIntent(String packageName, String className) {
		return new Intent().setComponent(new ComponentName(packageName, className));
	}
}
