package com.company.core.qrc;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

public class MD5Util {
    public static String getMD5Str(String sMsg) {
        MessageDigest messageDigest = null;
        try {
            byte[] msg = sMsg.getBytes("UTF-8");
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(msg);
        } catch (Exception localNoSuchAlgorithmException) {
        }
        byte[] b = messageDigest.digest();
        return new String(Base64.encodeBase64(b));
    }

    public static void main(String[] args) {
        String snr = "aaaa1111!";
        String sStr = getMD5Str(snr);
        System.out.println(sStr);
    }
}