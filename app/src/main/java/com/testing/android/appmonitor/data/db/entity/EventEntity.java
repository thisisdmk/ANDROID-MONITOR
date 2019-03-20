package com.testing.android.appmonitor.data.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "event")
final public class EventEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String appName;
    private String version;
    private String packageName;
    private String sha1Key;
    private Date dateAndTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSha1Key() {
        return sha1Key;
    }

    public void setSha1Key(String sha1Key) {
        this.sha1Key = sha1Key;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventEntity)) return false;

        EventEntity that = (EventEntity) o;

        if (getId() != that.getId()) return false;
        if (getAppName() != null ? !getAppName().equals(that.getAppName()) : that.getAppName() != null)
            return false;
        if (getVersion() != null ? !getVersion().equals(that.getVersion()) : that.getVersion() != null)
            return false;
        if (getPackageName() != null ? !getPackageName().equals(that.getPackageName()) : that.getPackageName() != null)
            return false;
        if (getSha1Key() != null ? !getSha1Key().equals(that.getSha1Key()) : that.getSha1Key() != null)
            return false;
        return getDateAndTime() != null ? getDateAndTime().equals(that.getDateAndTime()) : that.getDateAndTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getAppName() != null ? getAppName().hashCode() : 0);
        result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
        result = 31 * result + (getPackageName() != null ? getPackageName().hashCode() : 0);
        result = 31 * result + (getSha1Key() != null ? getSha1Key().hashCode() : 0);
        result = 31 * result + (getDateAndTime() != null ? getDateAndTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", appName='" + appName + '\'' +
                ", version='" + version + '\'' +
                ", packageName='" + packageName + '\'' +
                ", sha1Key='" + sha1Key + '\'' +
                ", dateAndTime=" + dateAndTime +
                '}';
    }
}
