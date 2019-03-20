package com.testing.android.appmonitor.data.eventdetails;

import android.content.Context;
import android.content.Intent;

import com.testing.android.appmonitor.domain.InstallationEvent;
import com.testing.android.appmonitor.domain.eventdetails.AppLauncher;

final public class AppLauncherImpl implements AppLauncher {
    private Context context;

    public AppLauncherImpl(Context context) {
        this.context = context;
    }

    @Override
    public void openApp(InstallationEvent item) {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(item.getPackageName());
        context.startActivity(launchIntent);
    }
}
