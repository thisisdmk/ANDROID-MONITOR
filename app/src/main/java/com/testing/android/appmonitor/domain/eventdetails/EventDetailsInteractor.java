package com.testing.android.appmonitor.domain.eventdetails;

import androidx.lifecycle.LiveData;

import com.testing.android.appmonitor.domain.InstallationEvent;

public interface EventDetailsInteractor {
    LiveData<InstallationEvent> loadInstallationDetails(int eventId);

    void openApp(InstallationEvent appInstallationEvent);
}
