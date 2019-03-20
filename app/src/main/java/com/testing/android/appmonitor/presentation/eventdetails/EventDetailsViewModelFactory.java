package com.testing.android.appmonitor.presentation.eventdetails;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.testing.android.appmonitor.MonitorApp;
import com.testing.android.appmonitor.domain.eventdetails.EventDetailsInteractorModel;

final public class EventDetailsViewModelFactory implements ViewModelProvider.Factory {
    private int eventId;
    private MonitorApp app;

    EventDetailsViewModelFactory(int eventId, MonitorApp app) {
        this.eventId = eventId;
        this.app = app;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EventDetailsViewModel(
                eventId,
                new EventDetailsInteractorModel(
                        app.getEventDetailsRepository(),
                        app.getAppLauncher()
                )
        );
    }
}
