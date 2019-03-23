package com.testing.android.appmonitor.presentation.updatejob;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ChangedPackages;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;

import static com.testing.android.appmonitor.presentation.updatejob.ApplicationUpdateNotifications.showNotification;

final class ApplicationUpdateUtils {
    private static final String PREF_SEQUENCE = "seq";

    static void onPackageUpdate(Context context, String packageName) {
        showNotification(context);
        if (packageName != null) {
            ApplicationUpdateJobService.launch(context, packageName);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    static void checkForUpdates(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        PackageManager pm = context.getPackageManager();

        int sequence = prefs.getInt(PREF_SEQUENCE, 0);
        ChangedPackages delta = pm.getChangedPackages(sequence);
        if (delta != null) {
            prefs.edit().putInt(PREF_SEQUENCE, delta.getSequenceNumber()).apply();
            if (sequence > 0) {
                for (String pkg : delta.getPackageNames()) {
                    ApplicationUpdateUtils.onPackageUpdate(context, pkg);
                }
            }
        }
    }
}
