package com.testing.android.appmonitor.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.testing.android.appmonitor.data.db.entity.EventEntity;

import java.util.List;

@Dao
public interface EventsDao {
    @Insert
    Long insertEvent(EventEntity event);

    @Query("SELECT * FROM event")
    LiveData<List<EventEntity>> loadAllEvents();

    @Query("SELECT * FROM event where event.id = :eventId")
    LiveData<EventEntity> loadEventById(int eventId);
}
