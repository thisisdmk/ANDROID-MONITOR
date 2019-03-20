package com.testing.android.appmonitor.presentation.updatejob;

import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

public interface ApplicationUpdateRepository {
    void updateAppData(@NonNull String packageName) throws PackageManager.NameNotFoundException;
}
