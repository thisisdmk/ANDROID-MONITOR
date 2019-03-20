package com.testing.android.appmonitor.domain;

final public class InstallationEvent {
    private final int id;
    private final String appName;
    private final String version;
    private final String packageName;
    private final String sha1Key;
    private final String dateAndTime;

    public InstallationEvent(int id, String appName, String version, String packageName, String sha1Key, String dateAndTime) {
        this.id = id;
        this.appName = appName;
        this.version = version;
        this.packageName = packageName;
        this.sha1Key = sha1Key;
        this.dateAndTime = dateAndTime;
    }

    public int getId() {
        return id;
    }

    public String getAppName() {
        return appName;
    }

    public String getVersion() {
        return version;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getSha1Key() {
        return sha1Key;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstallationEvent)) return false;

        InstallationEvent that = (InstallationEvent) o;

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
        return "InstallationEvent{" +
                "id=" + id +
                ", appName='" + appName + '\'' +
                ", version='" + version + '\'' +
                ", packageName='" + packageName + '\'' +
                ", sha1Key='" + sha1Key + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                '}';
    }
}
