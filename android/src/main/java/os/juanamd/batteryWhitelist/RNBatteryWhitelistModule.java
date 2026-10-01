package os.juanamd.batterywhitelist;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;

import java.util.ArrayList;
import java.util.List;

@ReactModule(name = RNBatteryWhitelistModule.NAME)
public class RNBatteryWhitelistModule extends NativeRNBatteryWhitelistSpec {

	public static final String NAME = "RNBatteryWhitelist";
	private static final String TAG = "RNBatteryWhitelist";

	public RNBatteryWhitelistModule(ReactApplicationContext reactContext) {
		super(reactContext);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void hasWhitelistIntent(final Promise promise) {
		try {
			final ReactApplicationContext context = this.getReactApplicationContext();
			promise.resolve(this.hasWhitelistIntent(context));
		} catch (Exception e) {
			promise.reject(e);
		}
	}

	private boolean hasWhitelistIntent(final ReactApplicationContext context) {
		for (Intent intent : Constants.getPowerManagerIntents()) {
			if (this.isCallable(intent, context)) return true;
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			return this.isCallable(Constants.getGenericBatteryOptimizationIntent(), context);
		}
		return false;
	}

	@Override
	public void startWhitelistActivity(final Promise promise) {
		try {
			final ReactApplicationContext context = this.getReactApplicationContext();
			ArrayList<Intent> intentList = this.getWhitelistIntentList(context);
			Exception lastException = null;

			for (Intent intent : intentList) {
				try {
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
					promise.resolve(null);
					return;
				} catch (Exception e) {
					lastException = e;
					Log.e(TAG, "Could not start whitelist activity", e);
				}
			}

			if (lastException != null) {
				promise.reject(lastException);
			} else {
				promise.reject(new Exception("No whitelist intent found"));
			}
		} catch (Exception e) {
			promise.reject(e);
		}
	}

	private ArrayList<Intent> getWhitelistIntentList(final ReactApplicationContext context) {
		ArrayList<Intent> intentList = new ArrayList<Intent>();
		for (Intent intent : Constants.getPowerManagerIntents()) {
			Intent resolvedIntent = new Intent(intent);
			if (this.isCallable(resolvedIntent, context)) {
				this.addPackageNameIfNeeded(resolvedIntent, context.getPackageName());
				intentList.add(resolvedIntent);
			}
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			Intent genericIntent = Constants.getGenericBatteryOptimizationIntent();
			if (this.isCallable(genericIntent, context)) intentList.add(genericIntent);
		}
		return intentList;
	}

	private boolean isCallable(final Intent intent, final ReactApplicationContext context) {
		if (intent == null) return false;
		try {
			List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, 0);
			return list.size() > 0;
		} catch (Exception e) {
			Log.w(TAG, "Could not resolve whitelist intent", e);
			return false;
		}
	}

	private void addPackageNameIfNeeded(final Intent intent, final String packageName) {
		try {
			if (intent.getComponent() == null) return;
			String className = intent.getComponent().getClassName();
			if (className.equals("com.meizu.safe.security.SHOW_APPSEC")) {
				intent.putExtra("packageName", packageName);
			} else if (className.equals("com.coloros.safecenter.startupapp.StartupAppListActivity")) {
				intent.setData(android.net.Uri.parse("package:" + packageName));
			}
		} catch (Exception e) {
			Log.e(TAG, "Could not add package name", e);
		}
	}
}
