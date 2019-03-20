package com.testing.android.appmonitor.data.db;

import androidx.room.TypeConverter;

import java.sql.Timestamp;
import java.util.Date;

final public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Timestamp(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
