package com.testing.android.appmonitor.data.updatejob;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

import com.testing.android.appmonitor.MonitorApp;
import com.testing.android.appmonitor.data.db.entity.EventEntity;
import com.testing.android.appmonitor.presentation.updatejob.ApplicationUpdateRepository;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

final public class ApplicationUpdateRepositoryImpl implements ApplicationUpdateRepository {
    private MonitorApp app;

    public ApplicationUpdateRepositoryImpl(MonitorApp app) {
        this.app = app;
    }

    @Override
    @SuppressLint("PackageManagerGetSignatures")
    public void updateAppData(@NonNull String packageName) throws PackageManager.NameNotFoundException {
        PackageManager pm = app.getPackageManager();
        PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
        ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);

        EventEntity event = new EventEntity();
        event.setAppName((String) pm.getApplicationLabel(applicationInfo));
        event.setDateAndTime(new Date(packageInfo.lastUpdateTime));
        event.setVersion(packageInfo.versionName);
        event.setPackageName(packageName);
        event.setSha1Key(getSHA1(packageInfo));

        app.getDb().eventsDao().insertEvent(event);
    }

    private String getSHA1(PackageInfo packageInfo) {
        String sha1;
        try {
            sha1 = KeyHelper.get(packageInfo, "SHA1");
        } catch (NoSuchAlgorithmException e) {
            sha1 = "-";
        }
        return sha1;
    }
}
