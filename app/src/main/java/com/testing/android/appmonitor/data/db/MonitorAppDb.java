package com.testing.android.appmonitor.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.testing.android.appmonitor.data.db.dao.EventsDao;
import com.testing.android.appmonitor.data.db.entity.EventEntity;


@TypeConverters({Converters.class})
@Database(entities = {
        EventEntity.class,
}, version = 1, exportSchema = false)
public abstract class MonitorAppDb extends RoomDatabase {
    private static final String DATABASE_NAME = "monitor_app_db";

    public abstract EventsDao eventsDao();

    private static MonitorAppDb INSTANCE;

    public static MonitorAppDb getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MonitorAppDb.class,
                    DATABASE_NAME
            ).build();
        }
        return INSTANCE;
    }
}