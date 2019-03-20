package com.testing.android.appmonitor.domain.eventdetails;

import androidx.lifecycle.LiveData;

import com.testing.android.appmonitor.domain.InstallationEvent;

final public class EventDetailsInteractorModel implements EventDetailsInteractor {

    private EventDetailsRepository repository;
    private AppLauncher launcher;

    public EventDetailsInteractorModel(EventDetailsRepository repository, AppLauncher launcher) {
        this.repository = repository;
        this.launcher = launcher;
    }

    @Override
    public LiveData<InstallationEvent> loadInstallationDetails(int eventId) {
        return repository.loadInstallationDetails(eventId);
    }

    @Override
    public void openApp(InstallationEvent item) {
        launcher.openApp(item);
    }
}
