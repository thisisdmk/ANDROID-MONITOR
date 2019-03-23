package com.testing.android.appmonitor.presentation.updatejob;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;

import com.testing.android.appmonitor.MonitorApp;

import java.util.concurrent.Future;

public class ApplicationUpdateJobService extends JobService {
    public static final String PACKAGE_NAME_KEY = "package_name_key";
    public static final int JOB_ID = 1288978234;

    private Future<?> asyncJob;

    @Override
    public boolean onStartJob(JobParameters params) {
        String packageName = params.getExtras().getString(PACKAGE_NAME_KEY);
        if (packageName == null) throw new IllegalArgumentException("PACKAGE_NAME_KEY is null");

        MonitorApp app = MonitorApp.get(getApplicationContext());
        asyncJob = app.getExecutor().submit(() -> {
            try {
                app.getApplicationUpdateRepository().updateAppData(packageName);
                jobFinished(params, false);
            } catch (Throwable x) {
                x.printStackTrace();
                jobFinished(params, true);
            }
        });
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        if (asyncJob != null) {
            asyncJob.cancel(false);
        }
        return true;
    }


    public static void launch(Context context, @NonNull String packageName) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
        PersistableBundle extras = new PersistableBundle();
        extras.putString(PACKAGE_NAME_KEY, packageName);
        JobInfo jobInfo = new JobInfo.Builder(
                JOB_ID,
                new ComponentName(
                        context,
                        ApplicationUpdateJobService.class
                ))
                .setOverrideDeadline(0)
                .setExtras(extras)
                .build();
        jobScheduler.schedule(jobInfo);
    }
}
