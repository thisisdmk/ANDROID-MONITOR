package com.testing.android.appmonitor.presentation.updatejob;

import android.content.Context;

import static com.testing.android.appmonitor.presentation.updatejob.ApplicationUpdateNotifications.showNotification;

final class ApplicationUpdateUtils {
    static void onPackageUpdate(Context context, String packageName) {
        showNotification(context);
        if (packageName != null) {
            ApplicationUpdateJobService.launch(context, packageName);
        }
    }
}
