package com.testing.android.appmonitor.presentation.eventslist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.testing.android.appmonitor.MonitorApp;

final public class EventsListViewModelFactory implements ViewModelProvider.Factory {

    private MonitorApp app;

    public EventsListViewModelFactory(MonitorApp app) {
        this.app = app;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EventsListViewModel(app.getEventsListRepository());
    }
}
