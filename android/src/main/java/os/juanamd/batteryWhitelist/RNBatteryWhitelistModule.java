package os.juanamd.batteryWhitelist;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.List;

public class RNBatteryWhitelistModule extends ReactContextBaseJavaModule {

	public RNBatteryWhitelistModule(ReactApplicationContext reactContext) {
		super(reactContext);
	}

	@Override
	public String getName() {
		return "RNBatteryWhitelist";
	}

	@ReactMethod
	public void hasWhitelistIntent(Callback cb) {
		ReactApplicationContext context = this.getReactApplicationContext();
		if (context != null) {
			Intent intent = this.getWhitelistIntent(context);
			boolean hasIntent = (intent != null) ? true : false;
			cb.invoke(hasIntent);
		}
	}

	@ReactMethod
	public void startWhitelistActivity() {
		ReactApplicationContext context = this.getReactApplicationContext();
		if (context != null) {
			Intent intent = this.getWhitelistIntent(context);
			if (intent != null) {
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				try {
					context.startActivity(intent);
				} catch (Exception exception) {
					Log.e("RNBatteryWhitelist", "Could not start whitelist activity", exception);
				}
			} else {
				Log.i("RNBatteryWhitelist", "No whitelist intent found");
			}
		}
	}

	private Intent getWhitelistIntent(ReactApplicationContext context) {
		for (Intent intent : Constants.POWERMANAGER_INTENTS) {
            if (this.isCallable(intent, context)) return intent;
		}
		return null;
	}

	private boolean isCallable(Intent intent, ReactApplicationContext context) {
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

}
