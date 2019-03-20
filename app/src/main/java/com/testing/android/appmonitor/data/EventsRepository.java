package com.testing.android.appmonitor.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.testing.android.appmonitor.data.db.dao.EventsDao;
import com.testing.android.appmonitor.data.db.entity.EventEntity;
import com.testing.android.appmonitor.domain.InstallationEvent;
import com.testing.android.appmonitor.domain.eventdetails.EventDetailsRepository;
import com.testing.android.appmonitor.domain.eventslist.EventsListRepository;

import java.util.ArrayList;
import java.util.List;

final public class EventsRepository implements EventsListRepository, EventDetailsRepository {

    private EventsDao dao;

    public EventsRepository(EventsDao dao) {
        this.dao = dao;
    }

    @Override
    public LiveData<InstallationEvent> loadInstallationDetails(int eventId) {
        return Transformations.map(
                dao.loadEventById(eventId),
                this::entityToItem
        );
    }

    @Override
    public LiveData<List<InstallationEvent>> loadEventsList() {
        return Transformations.map(
                dao.loadAllEvents(),
                this::entitiesToItems
        );
    }

    private InstallationEvent entityToItem(EventEntity entity) {
        return new InstallationEvent(
                entity.getId(),
                entity.getAppName(),
                entity.getVersion(),
                entity.getPackageName(),
                entity.getSha1Key(),
                entity.getDateAndTime().toString()
        );
    }

    private List<InstallationEvent> entitiesToItems(List<EventEntity> entities) {
        List<InstallationEvent> eventList = new ArrayList<>(entities.size());
        for (EventEntity entity : entities) {
            eventList.add(entityToItem(entity));
        }
        return eventList;
    }
}
