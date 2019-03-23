package com.testing.android.appmonitor.presentation.updatejob;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import static com.testing.android.appmonitor.presentation.updatejob.ApplicationUpdateUtils.checkForUpdates;

final public class ApplicationUpdatePollingService extends JobService {
    private static final int JOB_ID = 1337;


    @Override
    public boolean onStartJob(JobParameters params) {
        checkForUpdates(getApplicationContext());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static void launch(Context context) {
        checkForUpdates(context);

        ComponentName cn = new ComponentName(context, ApplicationUpdatePollingService.class);
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
