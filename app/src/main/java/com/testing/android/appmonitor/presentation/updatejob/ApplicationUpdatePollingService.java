package com.testing.android.appmonitor.presentation.updatejob;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ChangedPackages;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;

final public class ApplicationUpdatePollingService extends JobService {
    private static final int JOB_ID = 1337;
    private static final String PREF_SEQUENCE = "seq";

    @Override
    public boolean onStartJob(JobParameters params) {
        checkForUpdates(getApplicationContext());
        return false;
    }

    @TargetApi(Build.VERSION_CODES.O)
    private static void checkForUpdates(Context context) {
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

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static void launch(Context context) {
        checkForUpdates(context);

        ComponentName cn = new ComponentName(context, ApplicationUpdateJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, cn)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE)
                .setPeriodic(15 * 60 * 1000, 60000)
                .setPersisted(true)
                .setRequiresCharging(false)
                .setRequiresDeviceIdle(false);

        context.getSystemService(JobScheduler.class)
                .schedule(builder.build());
    }
}
