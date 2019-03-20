package com.testing.android.appmonitor.presentation.updatejob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.testing.android.appmonitor.R;

import static android.content.Context.JOB_SCHEDULER_SERVICE;
import static android.content.Intent.ACTION_PACKAGE_ADDED;
import static android.content.Intent.ACTION_PACKAGE_REPLACED;
import static com.testing.android.appmonitor.presentation.updatejob.ApplicationUpdateJobService.JOB_ID;
import static com.testing.android.appmonitor.presentation.updatejob.ApplicationUpdateJobService.PACKAGE_NAME_KEY;

final public class ApplicationUpdateBroadcastReceiver extends BroadcastReceiver {

    public static final int NOTIFICATION_ID = 132;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case ACTION_PACKAGE_REPLACED:
                case ACTION_PACKAGE_ADDED:
                    showNotification(context);

                    Uri data = intent.getData();
                    String packageName = data == null ? null : data.getSchemeSpecificPart();
                    if (packageName != null) {
                        launchApplicationUpdateJobService(context, packageName);
                    }
                    break;
            }
        }
    }

    private void showNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(context.getString(R.string.notification_application_update_title))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build());
    }

    private void launchApplicationUpdateJobService(Context context, @NonNull String packageName) {
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
