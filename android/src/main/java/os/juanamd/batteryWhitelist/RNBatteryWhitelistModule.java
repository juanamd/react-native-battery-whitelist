package os.juanamd.batteryWhitelist;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.ArrayList;
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
	public void hasWhitelistIntent(final Promise promise) {
		try {
			final ReactApplicationContext context = this.getReactApplicationContext();
			if (context == null) {
				promise.reject(new Exception("Null react application context"));
				return;
			}
			boolean hasIntent = this.hasWhitelistIntent(context);
			promise.resolve(hasIntent);
		} catch (Exception e) {
			promise.reject(e);
		}
	}

	private boolean hasWhitelistIntent(final ReactApplicationContext context) {
		for (Intent intent : Constants.POWERMANAGER_INTENTS) {
			if (this.isCallable(intent, context)) return true;
		}
		return false;
	}

	@ReactMethod
	public void startWhitelistActivity(final Promise promise) {
		try {
			final ReactApplicationContext context = this.getReactApplicationContext();
			if (context == null) {
				promise.reject(new Exception("Null react application context"));
				return;
			}
			ArrayList<Intent> intentList = this.getWhitelistIntentList(context);
			if (intentList.size() == 0) {
				promise.reject(new Exception("No whitelist intent found"));
				return;
			}
			for (Intent intent : intentList) {
				try {
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
					break;
				} catch (Exception e) {
					Log.e(TAG, "Could not start whitelist activity", e);
				}
			}
			promise.resolve(null);
		} catch (Exception e) {
			promise.reject(e);
		}
	}

	private ArrayList<Intent> getWhitelistIntentList(final ReactApplicationContext context) {
		ArrayList<Intent> intentList = new ArrayList<Intent>();
		for (Intent intent : Constants.POWERMANAGER_INTENTS) {
			if (this.isCallable(intent, context)) {
				this.addPackageNameIfNeeded(intent, context.getPackageName());
				intentList.add(intent);
			}
		}
		return intentList;
	}

	private boolean isCallable(final Intent intent, final ReactApplicationContext context) {
		if (intent == null) return false;
		List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	private void addPackageNameIfNeeded(final Intent intent, final String packageName) {
		try {
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
