package com.testing.android.appmonitor.presentation.updatejob;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.testing.android.appmonitor.R;

final class ApplicationUpdateNotifications {

    private static final int NOTIFICATION_ID = 132;
    private static final String CHANNEL_ID = "channelIdd";

    static void showNotification(Context context) {
        NotificationManager mgr =
                (NotificationManager) context.getSystemService(
                        Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && mgr.getNotificationChannel(CHANNEL_ID) == null) {
            mgr.createNotificationChannel(
                    new NotificationChannel(CHANNEL_ID, "Whatever", NotificationManager.IMPORTANCE_DEFAULT)
            );
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(context.getString(R.string.notification_application_update_title))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat
                .from(context)
                .notify(NOTIFICATION_ID, builder.build());
    }
}
