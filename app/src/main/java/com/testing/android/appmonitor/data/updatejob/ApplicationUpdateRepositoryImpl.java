package com.testing.android.appmonitor.data.updatejob;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.testing.android.appmonitor.MonitorApp;
import com.testing.android.appmonitor.data.db.entity.EventEntity;
import com.testing.android.appmonitor.presentation.updatejob.ApplicationUpdateRepository;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

final public class ApplicationUpdateRepositoryImpl implements ApplicationUpdateRepository {
    private MonitorApp app;

    public ApplicationUpdateRepositoryImpl(MonitorApp app) {
        this.app = app;
    }

    @Override
    @SuppressLint("PackageManagerGetSignatures")
    public void updateAppData(@NonNull String packageName) throws PackageManager.NameNotFoundException {
        PackageManager pm = app.getPackageManager();
        PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
        ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);

        EventEntity event = new EventEntity();
        event.setAppName((String) pm.getApplicationLabel(applicationInfo));
        event.setDateAndTime(new Date(packageInfo.lastUpdateTime));
        event.setVersion(packageInfo.versionName);
        event.setPackageName(packageName);
        event.setSha1Key(getSHA1(new File(applicationInfo.sourceDir)));

        app.getDb().eventsDao().insertEvent(event);
    }

    private String getSHA1(File apkFile) {

        try (FileInputStream in = new FileInputStream(apkFile);
             BufferedInputStream bis = new BufferedInputStream(in)) {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] bytes = new byte[524288];
            int read;
            while ((read = bis.read(bytes)) != -1)
                md.update(bytes, 0, read);

            return toHexString(md.digest());
        } catch (IOException e) {
            Log.e(ApplicationUpdateRepositoryImpl.class.getName(), "Can't read file : " + apkFile.getAbsolutePath());
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String toHexString(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }
}
