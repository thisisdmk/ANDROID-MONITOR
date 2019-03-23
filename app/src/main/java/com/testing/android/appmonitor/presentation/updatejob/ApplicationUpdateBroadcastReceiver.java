package com.testing.android.appmonitor.presentation.updatejob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;

import java.util.HashMap;

import static android.content.Intent.ACTION_PACKAGE_ADDED;
import static android.content.Intent.ACTION_PACKAGE_REPLACED;


final public class ApplicationUpdateBroadcastReceiver extends BroadcastReceiver {

    private static final long ADD_THEN_REPLACE_DELTA = 2000L;
    private static HashMap<String, Long> ADD_TIMESTAMPS = new HashMap<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        String packageName = data == null ? null : data.getSchemeSpecificPart();
        if (packageName == null) return;

        if (ACTION_PACKAGE_ADDED.equals(intent.getAction())) {
            ADD_TIMESTAMPS.put(packageName, SystemClock.uptimeMillis());
            ApplicationUpdateUtils.onPackageUpdate(context, packageName);
        } else if (ACTION_PACKAGE_REPLACED.equals(intent.getAction())) {
            Long added = ADD_TIMESTAMPS.get(packageName);
            if (added == null || (SystemClock.uptimeMillis() - added) > ADD_THEN_REPLACE_DELTA) {
                ApplicationUpdateUtils.onPackageUpdate(context, packageName);
                if (added != null) {
                    ADD_TIMESTAMPS.remove(packageName);
                }
            }
        }
    }
}
