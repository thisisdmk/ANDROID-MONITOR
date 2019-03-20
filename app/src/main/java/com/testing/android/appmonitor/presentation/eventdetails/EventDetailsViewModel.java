package com.testing.android.appmonitor.presentation.eventdetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.testing.android.appmonitor.domain.InstallationEvent;
import com.testing.android.appmonitor.domain.eventdetails.EventDetailsInteractor;

final class EventDetailsViewModel extends ViewModel {

    private LiveData<InstallationEvent> installationEventLiveData;
    private EventDetailsInteractor interactor;

    EventDetailsViewModel(int eventId, EventDetailsInteractor interactor) {
        this.interactor = interactor;
        installationEventLiveData = interactor.loadInstallationDetails(eventId);
    }

    LiveData<InstallationEvent> getEventDetails() {
        return installationEventLiveData;
    }

    void openApp() {
        interactor.openApp(installationEventLiveData.getValue());
    }
}
