package com.testing.android.appmonitor.data.updatejob;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class KeyHelper {

    /**
     * @param key string like: SHA1, SHA256, MD5.
     */
    @SuppressLint("PackageManagerGetSignatures") // test purpose
    static String get(PackageInfo info, String key) throws NoSuchAlgorithmException {
        Signature[] signatures = info.signatures;
        if (signatures.length > 0) {
            final MessageDigest md = MessageDigest.getInstance(key);
            md.update(signatures[0].toByteArray());

            final byte[] digest = md.digest();
            final StringBuilder toRet = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                if (i != 0) toRet.append(":");
                int b = digest[i] & 0xff;
                String hex = Integer.toHexString(b);
                if (hex.length() == 1) toRet.append("0");
                toRet.append(hex);
            }

            return toRet.toString();
        }
        return null;
    }
}
