package com.testing.android.appmonitor.domain.eventslist;

import androidx.lifecycle.LiveData;

import com.testing.android.appmonitor.domain.InstallationEvent;

import java.util.List;

public interface EventsListRepository {
    LiveData<List<InstallationEvent>> loadEventsList();
}
