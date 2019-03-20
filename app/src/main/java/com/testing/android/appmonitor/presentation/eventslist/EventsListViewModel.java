package com.testing.android.appmonitor.presentation.eventslist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.testing.android.appmonitor.domain.InstallationEvent;
import com.testing.android.appmonitor.domain.eventslist.EventsListRepository;

import java.util.List;

final class EventsListViewModel extends ViewModel {

    private LiveData<List<InstallationEvent>> eventsList;

    EventsListViewModel(EventsListRepository repo) {
        eventsList = repo.loadEventsList();
    }

    LiveData<List<InstallationEvent>> loadEventsList() {
        return eventsList;
    }
}
