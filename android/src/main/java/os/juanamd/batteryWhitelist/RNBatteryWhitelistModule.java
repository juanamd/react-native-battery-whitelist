package os.juanamd.batteryWhitelist;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.List;

public class RNBatteryWhitelistModule extends ReactContextBaseJavaModule {

	private static final String TAG = "RNBatteryWhitelist";

	public RNBatteryWhitelistModule(ReactApplicationContext reactContext) {
		super(reactContext);
	}

	@Override
	public String getName() {
		return TAG;
	}

	@ReactMethod
	public void hasWhitelistIntent(final Callback cb) {
		final ReactApplicationContext context = this.getReactApplicationContext();
		if (context != null) {
			final Intent intent = this.getWhitelistIntent(context);
			boolean hasIntent = (intent != null) ? true : false;
			try {
				cb.invoke(hasIntent);
			} catch (Exception e) {
				Log.e(TAG, "Callback was already invoked!", e);
			}
		}
	}

	@ReactMethod
	public void startWhitelistActivity() {
		final ReactApplicationContext context = this.getReactApplicationContext();
		if (context != null) {
			final Intent intent = this.getWhitelistIntent(context);
			if (intent != null) {
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				try {
					context.startActivity(intent);
				} catch (Exception exception) {
					Log.e(TAG, "Could not start whitelist activity", exception);
				}
			} else {
				Log.i(TAG, "No whitelist intent found");
			}
		}
	}

	private Intent getWhitelistIntent(final ReactApplicationContext context) {
		for (Intent intent : Constants.POWERMANAGER_INTENTS) {
			if (this.isCallable(intent, context)) return intent;
		}

		final Intent meizu = this.getMeizuDynamicIntent();
		if (this.isCallable(meizu, context)) return meizu;
		final Intent coloros = this.getColorosDynamicIntent();
		if (this.isCallable(coloros, context)) return coloros;

		return null;
	}

	private boolean isCallable(final Intent intent, final ReactApplicationContext context) {
		if (intent == null) return false;
		List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	private Intent getMeizuDynamicIntent() {
		final ReactApplicationContext context = this.getReactApplicationContext();
		if (context != null) {
			return new Intent()
				.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.security.SHOW_APPSEC"))
				.addCategory(Intent.CATEGORY_DEFAULT)
				.putExtra("packageName", context.getPackageName());
		}
		return null;
	}

	private Intent getColorosDynamicIntent() {
		final ReactApplicationContext context = this.getReactApplicationContext();
		if (context != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			return new Intent()
				.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity"))
				.setAction(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
				.setData(android.net.Uri.parse("package:" + context.getPackageName()));
		}
		return null;
	}

}
