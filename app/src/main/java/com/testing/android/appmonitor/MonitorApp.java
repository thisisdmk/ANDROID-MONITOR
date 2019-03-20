package com.testing.android.appmonitor;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.testing.android.appmonitor.data.EventsRepository;
import com.testing.android.appmonitor.data.db.MonitorAppDb;
import com.testing.android.appmonitor.data.eventdetails.AppLauncherImpl;
import com.testing.android.appmonitor.data.updatejob.ApplicationUpdateRepositoryImpl;
import com.testing.android.appmonitor.domain.eventdetails.AppLauncher;
import com.testing.android.appmonitor.domain.eventdetails.EventDetailsRepository;
import com.testing.android.appmonitor.domain.eventslist.EventsListRepository;
import com.testing.android.appmonitor.presentation.updatejob.ApplicationUpdateRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final public class MonitorApp extends Application {
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public static MonitorApp get(@NonNull Context context) {
        return (MonitorApp) context.getApplicationContext();
    }

    public MonitorAppDb getDb() {
        return MonitorAppDb.getAppDatabase(this);
    }

    public EventsListRepository getEventsListRepository() {
        return new EventsRepository(getDb().eventsDao());
    }

    public EventDetailsRepository getEventDetailsRepository() {
        return new EventsRepository(getDb().eventsDao());
    }

    public AppLauncher getAppLauncher() {
        return new AppLauncherImpl(this);
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public ApplicationUpdateRepository getApplicationUpdateRepository() {
        return new ApplicationUpdateRepositoryImpl(this);
    }
}
